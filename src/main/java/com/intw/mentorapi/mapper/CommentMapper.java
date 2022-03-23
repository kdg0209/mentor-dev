package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.Comment;
import org.mapstruct.Mapper;

@Mapper
public interface CommentMapper {

    void insertComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(long idx, String role, long userIdx);
}
