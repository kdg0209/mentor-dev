package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.BoardConfig;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.boardConfig.BoardConfigListDTO;
import com.intw.mentorapi.dto.boardConfig.BoardConfigViewDTO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BoardConfigMapper {

    int isBoardConfigExist(long idx);
    int isTypeExist(String type);

    List<BoardConfigListDTO> findAllBoardConfig(@Param("pageDTO") PageDTO pageDTO);
    void insertBoardConfig(BoardConfig boardConfig);
    BoardConfigViewDTO findOneBoardConfigByIdx(long idx);
    void updateBoardConfigByIdx(BoardConfig boardConfig);
    void deleteBoardConfigByIdx(long idx);
}
