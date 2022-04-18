package com.intw.mentorapi.service;

import com.intw.mentorapi.dao.BoardConfig;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.boardConfig.BoardConfigDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.BoardConfigException;
import com.intw.mentorapi.mapper.BoardConfigMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardConfigService {

    private final BoardConfigMapper boardConfigMapper;

    public ApiResponse lists(PageDTO pageDTO) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("boardConfigList", boardConfigMapper.findAllBoardConfig(pageDTO));
        return result;
    }

    @Transactional
    public ApiResponse write(BoardConfigDTO.BoardConfigInsertDTO params) {
        ResponseMap result = new ResponseMap();

        int isTypeCount = boardConfigMapper.isTypeExist(params.getType());

        if (isTypeCount > 0) {
            throw new BoardConfigException(ErrorCode.isBoardConfigTypeExistException);
        }

        BoardConfig boardConfig = BoardConfig.builder()
                                    .name(params.getName())
                                    .type(params.getType())
                                    .role(params.getRole())
                                    .listCount(params.getListCount())
                                    .pageCount(params.getPageCount())
                                    .build();

        boardConfigMapper.insertBoardConfig(boardConfig);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("boardConfig", boardConfigMapper.findOneBoardConfig(idx));
        return result;
    }

    @Transactional
    public ApiResponse update(BoardConfigDTO.BoardConfigUpdateDTO params) {
        ResponseMap result = new ResponseMap();

        int isBoardConfigExist = boardConfigMapper.isBoardConfigExist(params.getIdx());

        if (isBoardConfigExist == 0) {
            throw new BoardConfigException(ErrorCode.isBoardConfigNotFoundException);
        }

        BoardConfig boardConfig = BoardConfig.builder()
                .idx(params.getIdx())
                .name(params.getName())
                .role(params.getRole())
                .listCount(params.getListCount())
                .pageCount(params.getPageCount())
                .build();

        boardConfigMapper.updateBoardConfig(boardConfig);
        return result;
    }

    @Transactional
    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        boardConfigMapper.deleteBoardConfig(idx);
        return result;
    }
}
