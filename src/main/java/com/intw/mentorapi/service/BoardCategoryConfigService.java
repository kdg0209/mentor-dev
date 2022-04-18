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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardCategoryConfigService {

    private final BoardCategoryConfigMapper boardCategoryConfigMapper;

    public ApiResponse lists(PageDTO pageDTO) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("boardCategoryConfigList", boardCategoryConfigMapper.findAllCategoryConfig(pageDTO));
        return result;
    }

    @Transactional
    public ApiResponse write(BoardCategoryConfigDTO.BoardCategoryConfigInsertDTO params) {
        ResponseMap result = new ResponseMap();

        int isCategoryNameExist = boardCategoryConfigMapper.isCategoryNameExist(params.getName());

        if (isCategoryNameExist > 0) {
            throw new BoardCategoryConfigException(ErrorCode.isBoardCategoryConfigNameExistException);
        }

        BoardCategoryConfig boardCategoryConfig = BoardCategoryConfig.builder()
                                                    .name(params.getName())
                                                    .build();
        boardCategoryConfigMapper.insertBoardCategoryConfig(boardCategoryConfig);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("boardCategoryConfig", boardCategoryConfigMapper.findOneBoardCategoryConfig(idx));
        return result;
    }

    @Transactional
    public ApiResponse update(BoardCategoryConfigDTO.BoardCategoryConfigUpdateDTO params) {
        ResponseMap result = new ResponseMap();
        BoardCategoryConfig boardCategoryConfig = BoardCategoryConfig.builder()
                                                    .idx(params.getIdx())
                                                    .name(params.getName())
                                                    .build();
        boardCategoryConfigMapper.updateBoardCategoryConfig(boardCategoryConfig);
        return result;
    }

    @Transactional
    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        boardCategoryConfigMapper.deleteBoardCategoryConfig(idx);
        return result;
    }
}
