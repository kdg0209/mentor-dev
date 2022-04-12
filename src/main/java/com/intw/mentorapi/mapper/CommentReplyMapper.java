package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.CommentReply;
import com.intw.mentorapi.dto.commentReply.CommentViewDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CommentReplyMapper {

    CommentViewDTO findOneCommentReply(long idx);
    void insertCommentReply(CommentReply commentReply);
    void updateCommentReply(CommentReply commentReply);
    void deleteCommentReply(long idx, String role, long userIdx);
}
