package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.CommentReply;
import org.mapstruct.Mapper;

@Mapper
public interface CommentReplyMapper {

    void insertCommentReply(CommentReply commentReply);
    void updateCommentReply(CommentReply commentReply);
    void deleteCommentReply(long idx, String role, long userIdx);
}
