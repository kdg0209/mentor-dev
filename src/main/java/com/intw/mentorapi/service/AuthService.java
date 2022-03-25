package com.intw.mentorapi.service;

import com.intw.mentorapi.app.AuthController;
import com.intw.mentorapi.common.HashPassword;
import com.intw.mentorapi.dao.RefreshToken;
import com.intw.mentorapi.dao.RoleCode;
import com.intw.mentorapi.dao.User;
import com.intw.mentorapi.dto.auth.AuthDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.RoleCodeException;
import com.intw.mentorapi.exception.customException.UserException;
import com.intw.mentorapi.exception.customException.AuthenticationException;
import com.intw.mentorapi.handler.JwtProvider;
import com.intw.mentorapi.mapper.AuthMapper;
import com.intw.mentorapi.mapper.RoleCodeMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class.getName());
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final AuthMapper authMapper;
    private final RoleCodeMapper roleCodeMapper;
    private final ModelMapper modelMapper;

    public ApiResponse login(AuthDTO.LoginDTO loginDTO) {
        ResponseMap result = new ResponseMap();

        try {
            User user = (User)authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            ).getPrincipal();

            Map createToken = createTokenReturn(user);
            result.setResponseData("accessToken", createToken.get("accessToken"));
            result.setResponseData("refreshIdx", createToken.get("refreshIdx"));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new AuthenticationException(ErrorCode.UsernameOrPasswordNotFoundException);
        }

        return result;
    }

    public ApiResponse newAccessToken(AuthDTO.GetNewAccessTokenDTO getNewAccessTokenDTO, HttpServletRequest request){
        ResponseMap result = new ResponseMap();
        String refreshToken = authMapper.findOneRefreshToken(getNewAccessTokenDTO.getRefreshIdx());

        // AccessToken은 만료되었지만 RefreshToken은 만료되지 않은 경우
        if(jwtProvider.validateJwtToken(request, refreshToken)){
            User user = (User) jwtProvider.getAuthentication(refreshToken).getPrincipal();

            Map createToken = createTokenReturn(user);
            result.setResponseData("accessToken", createToken.get("accessToken"));
            result.setResponseData("refreshIdx", createToken.get("refreshIdx"));
        }else{
            // RefreshToken 또한 만료된 경우는 로그인을 다시 진행해야 한다.
            result.setResponseData("code", ErrorCode.ReLogin.getCode());
            result.setResponseData("message", ErrorCode.ReLogin.getMessage());
            result.setResponseData("HttpStatus", ErrorCode.ReLogin.getStatus());
        }
        return result;
    }

    public ApiResponse join(AuthDTO.JoinDTO joinDTO) {
        ResponseMap result = new ResponseMap();

        int isEmailCount = authMapper.isEmailExist(joinDTO.getEmail());
        int isPhoneCount = authMapper.isPhoneExist(joinDTO.getPhone());
        RoleCode isRoleExist = roleCodeMapper.isRoleExist(joinDTO.getRoleCodeIdx());

        if (isRoleExist == null) {
            throw new RoleCodeException(ErrorCode.isRoleNotFoundException);
        }
        if (isEmailCount > 0) {
            throw new UserException(ErrorCode.isEmailExistException);
        }

        if (isPhoneCount > 0) {
            throw new UserException(ErrorCode.isPhoneExistException);
        }

        joinDTO.setPassword(new HashPassword().hashPassword(joinDTO.getPassword()));
        User user = modelMapper.map(joinDTO, User.class);
        authMapper.insertUser(user);
        return result;
    }

    /**
     *
     * @param user
     * @return accessToken 새로운 토큰
     * @return refreshIdx DB의 새로운 토큰 IDX
     */
    private Map<String, String> createTokenReturn(User user) {
        Map result = new HashMap();

        String accessToken = jwtProvider.createAccessToken(user);
        String refreshToken = jwtProvider.createRefreshToken(user).get("refreshToken");
        String refreshTokenExpirationAt = jwtProvider.createRefreshToken(user).get("refreshTokenExpirationAt");

        RefreshToken insertRefreshToken = new RefreshToken();
        insertRefreshToken.setUserEmail(user.getEmail());
        insertRefreshToken.setAccessToken(accessToken);
        insertRefreshToken.setRefreshToken(refreshToken);
        insertRefreshToken.setRefreshTokenExpirationAt(refreshTokenExpirationAt);
        authMapper.insertOrUpdateRefreshToken(insertRefreshToken);

        result.put("accessToken", accessToken);
        result.put("refreshIdx", insertRefreshToken.getIdx());
        return result;
    }
}
