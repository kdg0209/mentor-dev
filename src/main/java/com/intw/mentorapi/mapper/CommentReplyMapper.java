package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.CommentReply;
import com.intw.mentorapi.dto.commentReply.CommentViewDTO;
import com.intw.mentorapi.status.RoleStatus;
import org.mapstruct.Mapper;

@Mapper
public interface CommentReplyMapper {

    int isCommentReplyExist(long idx);

    CommentViewDTO findOneCommentReply(long idx);
    void insertCommentReply(CommentReply commentReply);
    void updateCommentReply(CommentReply commentReply);
    void deleteCommentReply(long idx, RoleStatus role, long userIdx);
}
