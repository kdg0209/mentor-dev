package com.intw.mentorapi.app;

import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Board / 사용자")
@RequestMapping("/app/board")
@RestController
@RequiredArgsConstructor
public class BoardController {

    @GetMapping("/lists")
    @ApiOperation(value="사용자 계정 목록")
    public ApiResponse lists(){
        ResponseMap result = new ResponseMap();
        result.setResponseData("홍길동", 10);
        result.setResponseData("이순신", 20);
        result.setResponseData("유관순", 30);
        result.setResponseData("주몽", 40);

        return result;
    }

    @PostMapping("/write")
    @ApiOperation(value="사용자 계정 등록")
    public ApiResponse write(){
        ResponseMap result = new ResponseMap();
        result.setResponseData("홍길동", 10);
        result.setResponseData("이순신", 20);
        result.setResponseData("유관순", 30);
        result.setResponseData("주몽", 40);

        return result;
    }

}
