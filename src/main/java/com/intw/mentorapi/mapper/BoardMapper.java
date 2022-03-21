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

    List<BoardListDTO> findAllBoard(@Param("pageDTO") PageDTO pageDTO);
    void insertBoard(Board board);
    BoardViewDTO findOneBoardByIdx(long idx);
    List<FileListDTO> findAllFilesByBoard(long idx);
    void updateBoardViewCountByIdx(long idx);
    void updateBoardByIdx(Board board);
    void deleteBoardByIdx(long idx);
}
