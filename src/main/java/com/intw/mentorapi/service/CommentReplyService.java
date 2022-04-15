package com.intw.mentorapi.service;

import com.intw.mentorapi.common.GetUserIp;
import com.intw.mentorapi.dao.CommentReply;
import com.intw.mentorapi.dto.commentReply.CommentReplyDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.CommentException;
import com.intw.mentorapi.exception.customException.CommentReplyException;
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

    public ApiResponse write(CommentReplyDTO.CommentReplyInsertDTO params) {
        ResponseMap result = new ResponseMap();
        GetUserIp getUserIp = new GetUserIp();

        int isCommentExist = commentMapper.isCommentExist(params.getCommentIdx());

        if (isCommentExist == 0) {
            throw new CommentException(ErrorCode.isCommentNotFoundException);
        }

        CommentReply commentReply = CommentReply.builder()
                                        .userIdx(getUser().getIdx())
                                        .commentIdx(params.getCommentIdx())
                                        .reply(params.getReply())
                                        .writeIp(getUserIp.returnIP())
                                        .build();

        commentReplyMapper.insertCommentReply(commentReply);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("commentReply", commentReplyMapper.findOneCommentReply(idx));
        return result;
    }

    public ApiResponse update(CommentReplyDTO.CommentReplyUpdateDTO params) {
        ResponseMap result = new ResponseMap();

        int isCommentReplyExist = commentReplyMapper.isCommentReplyExist(params.getIdx());

        if (isCommentReplyExist == 0) {
            throw new CommentReplyException(ErrorCode.isCommentReplyNotFoundException);
        }

        CommentReply commentReply = CommentReply.builder()
                                        .idx(params.getIdx())
                                        .userIdx(getUser().getIdx())
                                        .commentIdx(params.getCommentIdx())
                                        .reply(params.getReply())
                                        .build();

        commentReplyMapper.updateCommentReply(commentReply);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        commentReplyMapper.deleteCommentReply(idx, getUser().getRole(), getUser().getIdx());
        return result;
    }
}
