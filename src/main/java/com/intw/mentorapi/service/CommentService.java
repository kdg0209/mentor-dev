package com.intw.mentorapi.service;

import com.intw.mentorapi.common.GetUserIp;
import com.intw.mentorapi.dao.Comment;
import com.intw.mentorapi.dto.comment.CommentDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.BoardException;
import com.intw.mentorapi.exception.customException.CommentException;
import com.intw.mentorapi.handler.UserProvider;
import com.intw.mentorapi.mapper.BoardMapper;
import com.intw.mentorapi.mapper.CommentMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService extends UserProvider {

    private final BoardMapper boardMapper;
    private final CommentMapper commentMapper;

    public ApiResponse write(CommentDTO.CommentInsertDTO params) {
        ResponseMap result = new ResponseMap();
        GetUserIp getUserIp = new GetUserIp();

        int isBoardExist = boardMapper.isBoardExist(params.getBoardIdx());

        if (isBoardExist == 0) {
            throw new BoardException(ErrorCode.isBoardNotFoundException);
        }

        Comment comment = Comment.builder()
                            .userIdx(getUser().getIdx())
                            .boardIdx(params.getBoardIdx())
                            .comment(params.getComment())
                            .writeIp(getUserIp.returnIP())
                            .build();
        commentMapper.insertComment(comment);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();

        result.setResponseData("comment", commentMapper.findOneComment(idx));
        return result;
    }


    public ApiResponse update(CommentDTO.CommentUpdateDTO params) {
        ResponseMap result = new ResponseMap();

        int isCommentExist = commentMapper.isCommentExist(params.getIdx());

        if (isCommentExist == 0) {
            throw new CommentException(ErrorCode.isCommentNotFoundException);
        }

        Comment comment = Comment.builder()
                            .idx(params.getIdx())
                            .userIdx(getUser().getIdx())
                            .boardIdx(params.getBoardIdx())
                            .comment(params.getComment())
                            .build();

        commentMapper.updateComment(comment);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        commentMapper.deleteComment(idx, getUser().getRole(), getUser().getIdx());
        return result;
    }
}
