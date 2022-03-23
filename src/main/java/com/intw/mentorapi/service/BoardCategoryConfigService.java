package com.intw.mentorapi.service;

import com.intw.mentorapi.dao.BoardCategoryConfig;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.boardCategoryConfig.BoardCategoryConfigDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.BoardCategoryConfigException;
import com.intw.mentorapi.mapper.BoardCategoryConfigMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCategoryConfigService {

    private final ModelMapper modelMapper;
    private final BoardCategoryConfigMapper boardCategoryConfigMapper;

    public ApiResponse lists(PageDTO pageDTO) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("boardCategoryConfigList", boardCategoryConfigMapper.findAllCategoryConfig(pageDTO));
        return result;
    }

    public ApiResponse write(BoardCategoryConfigDTO.BoardCategoryConfigInsertDTO boardCategoryConfigInsertDTO) {
        ResponseMap result = new ResponseMap();

        int isCategoryCount = boardCategoryConfigMapper.isCategoryNameExist(boardCategoryConfigInsertDTO.getName());

        if (isCategoryCount > 0) {
            throw new BoardCategoryConfigException(ErrorCode.isBoardCategoryConfigNameExistException);
        }

        BoardCategoryConfig boardCategoryConfig = modelMapper.map(boardCategoryConfigInsertDTO, BoardCategoryConfig.class);
        boardCategoryConfigMapper.insertBoardCategoryConfig(boardCategoryConfig);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("boardCategoryConfig", boardCategoryConfigMapper.findOneBoardCategoryConfig(idx));
        return result;
    }

    public ApiResponse update(BoardCategoryConfigDTO.BoardCategoryConfigUpdateDTO boardCategoryConfigUpdateDTO) {
        ResponseMap result = new ResponseMap();
        BoardCategoryConfig boardCategoryConfig = modelMapper.map(boardCategoryConfigUpdateDTO, BoardCategoryConfig.class);
        boardCategoryConfigMapper.updateBoardCategoryConfig(boardCategoryConfig);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        boardCategoryConfigMapper.deleteBoardCategoryConfig(idx);
        return result;
    }
}
