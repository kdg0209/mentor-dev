package com.intw.mentorapi.service;

import com.intw.mentorapi.app.AuthController;
import com.intw.mentorapi.common.HashPassword;
import com.intw.mentorapi.dao.RefreshToken;
import com.intw.mentorapi.dao.User;
import com.intw.mentorapi.dto.auth.AuthDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.AuthException;
import com.intw.mentorapi.exception.customException.AuthenticationException;
import com.intw.mentorapi.handler.JwtProvider;
import com.intw.mentorapi.mapper.AuthMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final ModelMapper modelMapper;

    public ApiResponse login(AuthDTO.LoginDTO loginDTO) {
        ResponseMap result = new ResponseMap();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );

            Map createToken = createTokenReturn(loginDTO);
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
        String refreshToken = authMapper.findRefreshTokenByIdx(getNewAccessTokenDTO.getRefreshIdx());

        // AccessToken은 만료되었지만 RefreshToken은 만료되지 않은 경우
        if(jwtProvider.validateJwtToken(request, refreshToken)){
            String email = jwtProvider.getUserInfo(refreshToken);
            AuthDTO.LoginDTO loginDTO = new AuthDTO.LoginDTO();
            loginDTO.setEmail(email);

            Map createToken = createTokenReturn(loginDTO);
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

        if (isEmailCount > 0) {
            throw new AuthException(ErrorCode.isEmailExistException);
        }

        if (isPhoneCount > 0) {
            throw new AuthException(ErrorCode.isPhoneExistException);
        }

        joinDTO.setPassword(new HashPassword().hashPassword(joinDTO.getPassword()));
        User user = modelMapper.map(joinDTO, User.class);

        authMapper.insertUser(user);
        return result;
    }

    // 토큰을 생성해서 반환
    private Map<String, String> createTokenReturn(AuthDTO.LoginDTO loginDTO) {
        Map result = new HashMap();

        String accessToken = jwtProvider.createAccessToken(loginDTO);
        String refreshToken = jwtProvider.createRefreshToken(loginDTO).get("refreshToken");
        String refreshTokenExpirationAt = jwtProvider.createRefreshToken(loginDTO).get("refreshTokenExpirationAt");

        RefreshToken insertRefreshToken = RefreshToken.builder()
                .userEmail(loginDTO.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .refreshTokenExpirationAt(refreshTokenExpirationAt)
                .build();

        authMapper.insertOrUpdateRefreshToken(insertRefreshToken);

        result.put("accessToken", accessToken);
        result.put("refreshIdx", insertRefreshToken.getIdx());
        return result;
    }
}
