package com.intw.mentorapi.app;

import com.intw.mentorapi.dto.comment.CommentDTO;
import com.intw.mentorapi.dto.commentReply.CommentReplyDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.CommentReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Comment Reply / 댓글 답변")
@RequestMapping("/app/comment-reply")
@RestController
@RequiredArgsConstructor
public class CommentReplyController {

    private final CommentReplyService commentReplyService;

    @PostMapping
    @ApiOperation(value="댓글 답변 작성")
    public ApiResponse write(@RequestBody @Valid CommentReplyDTO.CommentReplyInsertDTO commentReplyInsertDTO) {
        return commentReplyService.write(commentReplyInsertDTO);
    }

    @GetMapping("/{idx}")
    @ApiOperation(value="댓글 답변 조회")
    public ApiResponse view(@PathVariable("idx") long idx) {
        return commentReplyService.view(idx);
    }

    @PutMapping
    @ApiOperation(value="댓글 답변 수정")
    public ApiResponse update(@RequestBody @Valid CommentReplyDTO.CommentReplyUpdateDTO commentReplyUpdateDTO) {
        return commentReplyService.update(commentReplyUpdateDTO);
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value="댓글 답변 삭제")
    public ApiResponse delete(@PathVariable("idx") long idx) {
        return commentReplyService.delete(idx);
    }
}
