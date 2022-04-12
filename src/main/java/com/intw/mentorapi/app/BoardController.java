package com.intw.mentorapi.app;

import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.board.BoardDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Board / 게시판")
@RequestMapping("/app/board")
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    @ApiOperation(value="게시판 목록")
    public ApiResponse index(@Valid PageDTO pageDTO) {
        return boardService.lists(pageDTO);
    }

    @GetMapping("/{idx}")
    @ApiOperation(value="게시판 조회")
    public ApiResponse view(@PathVariable("idx") long idx) {
        return boardService.view(idx);
    }
}
