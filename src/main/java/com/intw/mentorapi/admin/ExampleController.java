package com.intw.mentorapi.admin;

import com.intw.mentorapi.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Example / example")
@RequestMapping("/admin/example")
@RestController
@RequiredArgsConstructor
public class ExampleController {

//    sudo docker stop "sudo docker ps -q"
//    sudo docker rmi --force "cova1256/dev"

    @GetMapping("/lists")
    @ApiOperation(value="test 목록")
    public ApiResponse lists() {
        return null;
    }

    @GetMapping("/lists/1")
    @ApiOperation(value="test 목록1")
    public ApiResponse liststest() {
        return null;
    }


    @GetMapping("/lists/2")
    @ApiOperation(value="test 목록2")
    public ApiResponse liststest2() {
        return null;
    }
}
