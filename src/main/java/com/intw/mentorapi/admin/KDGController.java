package com.intw.mentorapi.admin;

import com.intw.mentorapi.dto.user.UserDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "KDG / 사용자")
@RequestMapping("/admin/kdg")
@RestController
@RequiredArgsConstructor
public class KDGController {

    private final UserService userService;

    @GetMapping("/lists")
    @ApiOperation(value="사용자 계정 목록")
    public ApiResponse lists() {
        return userService.lists();
    }
}
