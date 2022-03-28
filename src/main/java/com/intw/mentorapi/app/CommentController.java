package com.intw.mentorapi.app;

import com.intw.mentorapi.dto.comment.CommentDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Comment / 댓글")
@RequestMapping("/app/comment")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ApiOperation(value="댓글 작성")
    public ApiResponse write(@RequestBody @Valid CommentDTO.CommentInsertDTO commentInsertDTO) {
        return commentService.write(commentInsertDTO);
    }

    @GetMapping("/{idx}")
    @ApiOperation(value="댓글 조회")
    public ApiResponse view(@PathVariable("idx") long idx) {
        return commentService.view(idx);
    }

    @PutMapping
    @ApiOperation(value="댓글 수정")
    public ApiResponse update(@RequestBody @Valid CommentDTO.CommentUpdateDTO commentUpdateDTO) {
        return commentService.update(commentUpdateDTO);
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value="댓글 삭제")
    public ApiResponse delete(@PathVariable("idx") long idx) {
        return commentService.delete(idx);
    }
}
