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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardConfigService {

    private final ModelMapper modelMapper;
    private final BoardConfigMapper boardConfigMapper;

    public ApiResponse lists(PageDTO pageDTO) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("boardConfigList", boardConfigMapper.findAllBoardConfig(pageDTO));
        return result;
    }

    public ApiResponse write(BoardConfigDTO.BoardConfigInsertDTO boardConfigInsertDTO) {
        ResponseMap result = new ResponseMap();

        int isTypeCount = boardConfigMapper.isTypeExist(boardConfigInsertDTO.getType());

        if (isTypeCount > 0) {
            throw new BoardConfigException(ErrorCode.isBoardConfigTypeExistException);
        }

        BoardConfig boardConfig = modelMapper.map(boardConfigInsertDTO, BoardConfig.class);
        boardConfigMapper.insertBoardConfig(boardConfig);
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("boardConfig", boardConfigMapper.findOneBoardConfig(idx));
        return result;
    }

    public ApiResponse update(BoardConfigDTO.BoardConfigUpdateDTO boardConfigUpdateDTO) {
        ResponseMap result = new ResponseMap();
        BoardConfig boardConfig = modelMapper.map(boardConfigUpdateDTO, BoardConfig.class);
        boardConfigMapper.updateBoardConfig(boardConfig);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();
        boardConfigMapper.deleteBoardConfig(idx);
        return result;
    }
}
