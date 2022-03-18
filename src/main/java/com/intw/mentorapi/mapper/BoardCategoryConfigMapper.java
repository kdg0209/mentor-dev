package com.intw.mentorapi.mapper;

import com.intw.mentorapi.dao.BoardCategoryConfig;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.boardCategoryConfig.BoardCategoryConfigListDTO;
import com.intw.mentorapi.dto.boardCategoryConfig.BoardCategoryConfigViewDTO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BoardCategoryConfigMapper {

    int isCategoryExist(long idx);
    int isCategoryNameExist(String name);

    List<BoardCategoryConfigListDTO> findAllCategoryConfig(@Param("pageDTO") PageDTO pageDTO);
    void insertBoardCategoryConfig(BoardCategoryConfig boardCategoryConfig);
    BoardCategoryConfigViewDTO findOneBoardCategoryConfigByIdx(long idx);
    void updateBoardCategoryConfigByIdx(BoardCategoryConfig boardCategoryConfig);
    void deleteBoardCategoryConfigByIdx(long idx);

}
