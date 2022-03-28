package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.Comment;
import com.intw.mentorapi.dto.comment.CommentListDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    int isCommentExist(long idx);

    List<CommentListDTO> findAllCommentByBoard(long boardIdx);
    Comment findOneComment(long idx);
    void insertComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(long idx, String role, long userIdx);
}
