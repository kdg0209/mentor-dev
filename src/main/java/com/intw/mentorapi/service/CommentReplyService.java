package com.intw.mentorapi.service;

import com.intw.mentorapi.common.GetUserIp;
import com.intw.mentorapi.dao.Comment;
import com.intw.mentorapi.dao.CommentReply;
import com.intw.mentorapi.dto.comment.CommentDTO;
import com.intw.mentorapi.dto.commentReply.CommentReplyDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.BoardException;
import com.intw.mentorapi.exception.customException.CommentException;
import com.intw.mentorapi.handler.UserProvider;
import com.intw.mentorapi.mapper.CommentMapper;
import com.intw.mentorapi.mapper.CommentReplyMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentReplyService extends UserProvider {

    private final CommentMapper commentMapper;
    private final CommentReplyMapper commentReplyMapper;

    public ApiResponse write(CommentReplyDTO.CommentReplyInsertDTO commentReplyInsertDTO) {
        ResponseMap result = new ResponseMap();
        GetUserIp getUserIp = new GetUserIp();

        int isCommentExist = commentMapper.isCommentExist(commentReplyInsertDTO.getCommentIdx());

        if (isCommentExist == 0) {
            throw new CommentException(ErrorCode.isCommentNotFoundException);
        }

        CommentReply commentReply = new CommentReply();
        commentReply.setUserIdx(getUser().getIdx());
        commentReply.setCommentIdx(commentReplyInsertDTO.getCommentIdx());
        commentReply.setReply(commentReplyInsertDTO.getReply());
        commentReply.setWriteIp(getUserIp.returnIP());

        commentReplyMapper.insertCommentReply(commentReply);
        return result;
    }

    public ApiResponse update(CommentReplyDTO.CommentReplyUpdateDTO commentReplyUpdateDTO) {
        ResponseMap result = new ResponseMap();

        CommentReply commentReply = new CommentReply();
        commentReply.setIdx(commentReplyUpdateDTO.getIdx());
        commentReply.setUserIdx(getUser().getIdx());
        commentReply.setCommentIdx(commentReplyUpdateDTO.getCommentIdx());
        commentReply.setReply(commentReplyUpdateDTO.getReply());

        commentReplyMapper.updateCommentReply(commentReply);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();

        commentReplyMapper.deleteCommentReply(idx, getUser().getRole(), getUser().getIdx());
        return result;
    }
}
