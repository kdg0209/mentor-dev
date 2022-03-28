package com.intw.mentorapi.service;

import com.intw.mentorapi.common.GetUserIp;
import com.intw.mentorapi.common.Role;
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

    public ApiResponse write(CommentDTO.CommentInsertDTO commentInsertDTO) {
        ResponseMap result = new ResponseMap();
        GetUserIp getUserIp = new GetUserIp();

        int isBoardExist = boardMapper.isBoardExist(commentInsertDTO.getBoardIdx());

        if (isBoardExist == 0) {
            throw new BoardException(ErrorCode.isBoardNotFoundException);
        }

        Comment comment = new Comment();
        comment.setUserIdx(getUser().getIdx());
        comment.setBoardIdx(commentInsertDTO.getBoardIdx());
        comment.setComment(commentInsertDTO.getComment());
        comment.setWriteIp(getUserIp.returnIP());

        commentMapper.insertComment(comment);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();

        result.setResponseData("comment", commentMapper.findOneComment(idx));
        return result;
    }


    public ApiResponse update(CommentDTO.CommentUpdateDTO commentUpdateDTO) {
        ResponseMap result = new ResponseMap();

        int isCommentExist = commentMapper.isCommentExist(commentUpdateDTO.getIdx());

        if (isCommentExist == 0) {
            throw new CommentException(ErrorCode.isCommentNotFoundException);
        }

        Comment comment = new Comment();
        comment.setIdx(commentUpdateDTO.getIdx());
        comment.setUserIdx(getUser().getIdx());
        comment.setBoardIdx(commentUpdateDTO.getBoardIdx());
        comment.setComment(commentUpdateDTO.getComment());

        commentMapper.updateComment(comment);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();

        commentMapper.deleteComment(idx, getUser().getRole(), getUser().getIdx());
        return result;
    }
}
