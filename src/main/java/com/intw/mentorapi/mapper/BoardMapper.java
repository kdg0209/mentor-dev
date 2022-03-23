package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.Board;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.board.BoardListDTO;
import com.intw.mentorapi.dto.board.BoardViewDTO;
import com.intw.mentorapi.dto.file.FileListDTO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<FileListDTO> findAllFilesByBoard(long idx);
    void updateBoardViewCount(long idx);

    List<BoardListDTO> findAllBoard(@Param("pageDTO") PageDTO pageDTO);
    BoardViewDTO findOneBoard(long idx);
    void insertBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(long idx);
}
