package com.intw.mentorapi.admin;

import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.boardConfig.BoardConfigDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.BoardConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Board Config / 게시판 설정")
@RequestMapping("/admin/board-config")
@RestController
@RequiredArgsConstructor
public class BoardConfigController {

    private final BoardConfigService boardConfigService;

    @GetMapping("/index")
    @ApiOperation(value="게시판 설정 목록")
    public ApiResponse index(@Valid PageDTO pageDTO) {
        return boardConfigService.lists(pageDTO);
    }

    @PostMapping
    @ApiOperation(value="게시판 설정 작성")
    public ApiResponse write(@RequestBody @Valid BoardConfigDTO.BoardConfigInsertDTO boardConfigInsertDTO) {
        return boardConfigService.write(boardConfigInsertDTO);
    }


    @GetMapping("/view/{idx}")
    @ApiOperation(value="게시판 설정 조회")
    public ApiResponse view(@PathVariable("idx") long idx) {
        return boardConfigService.view(idx);
    }

    @PutMapping
    @ApiOperation(value="게시판 설정 수정")
    public ApiResponse update(@RequestBody @Valid BoardConfigDTO.BoardConfigUpdateDTO boardConfigUpdateDTO) {
        return boardConfigService.update(boardConfigUpdateDTO);
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value="게시판 설정 삭제")
    public ApiResponse delete(@PathVariable("idx") long idx) {
        return boardConfigService.delete(idx);
    }
}
