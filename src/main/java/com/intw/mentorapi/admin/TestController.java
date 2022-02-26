package com.intw.mentorapi.admin;

import com.intw.mentorapi.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Test / test")
@RequestMapping("/admin/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/lists")
    @ApiOperation(value="test 목록")
    public ApiResponse lists() {
        return null;
    }
}
