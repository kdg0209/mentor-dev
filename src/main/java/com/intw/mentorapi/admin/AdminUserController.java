package com.intw.mentorapi.admin;

import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.user.UserDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "User / 사용자")
@RequestMapping("/admin/user")
@RestController
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;

    @GetMapping
    @ApiOperation(value="사용자 계정 목록")
    public ApiResponse index(@Valid PageDTO pageDTO) {
        return userService.lists(pageDTO);
    }

    @PostMapping
    @ApiOperation(value="사용자 계정 등록")
    public ApiResponse write(@RequestBody @Valid UserDTO.UserInsertDTO userDTO) {
        return userService.write(userDTO);
    }

    @GetMapping("/{idx}")
    @ApiOperation(value="사용자 계정 조회")
    public ApiResponse view(@PathVariable("idx") long idx) {
        return userService.view(idx);
    }

    @PutMapping
    @ApiOperation(value="사용자 계정 수정")
    public ApiResponse update(@RequestBody @Valid UserDTO.UserUpdateDTO userDTO) {
        return userService.update(userDTO);
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value="사용자 계정 삭제")
    public ApiResponse delete(@PathVariable("idx") long idx) {
        return userService.delete(idx);
    }
}
