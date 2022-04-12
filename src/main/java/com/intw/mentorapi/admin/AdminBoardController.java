package com.intw.mentorapi.admin;

import com.intw.mentorapi.common.S3Uploader;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.board.BoardDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Board / 게시판")
@RequestMapping("/admin/board")
@RestController
@RequiredArgsConstructor
public class AdminBoardController {

    private final BoardService boardService;
    private final S3Uploader s3Uploader;

    @GetMapping
    @ApiOperation(value="게시판 목록")
    public ApiResponse index(@Valid PageDTO pageDTO) {
        return boardService.lists(pageDTO);
    }

    @PostMapping
    @ApiOperation(value="게시판 작성")
    public ApiResponse write(@Valid BoardDTO.BoardInsertDTO boardInsertDTO) {
        return boardService.write(boardInsertDTO);
    }
    @GetMapping("/{idx}")
    @ApiOperation(value="게시판 조회")
    public ApiResponse view(@PathVariable("idx") long idx) {
        return boardService.view(idx);
    }

    @PutMapping
    @ApiOperation(value="게시판 수정")
    public ApiResponse update(@Valid BoardDTO.BoardUpdateDTO boardUpdateDTO) {
        return boardService.update(boardUpdateDTO);
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value="게시판 삭제")
    public ApiResponse delete(@PathVariable("idx") long idx) {
        return boardService.delete(idx);
    }
}