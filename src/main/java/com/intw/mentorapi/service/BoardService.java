package com.intw.mentorapi.service;

import com.intw.mentorapi.dao.Board;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.board.BoardDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.BoardCategoryConfigException;
import com.intw.mentorapi.exception.customException.BoardConfigException;
import com.intw.mentorapi.handler.UserProvider;
import com.intw.mentorapi.mapper.BoardCategoryConfigMapper;
import com.intw.mentorapi.mapper.BoardConfigMapper;
import com.intw.mentorapi.mapper.BoardMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService extends UserProvider {

    private final ModelMapper modelMapper;
    private final BoardMapper boardMapper;
    private final BoardCategoryConfigMapper boardCategoryConfigMapper;
    private final BoardConfigMapper boardConfigMapper;
    private final FileService fileService;

    public ApiResponse lists(PageDTO pageDTO) {
        ResponseMap result = new ResponseMap();
        result.setResponseData("boardList", boardMapper.findAllBoard(pageDTO));
        return result;
    }

    public ApiResponse write(BoardDTO.BoardInsertDTO boardInsertDTO) {
        ResponseMap result = new ResponseMap();

        int isBoardConfigExist = boardConfigMapper.isBoardConfigExist(boardInsertDTO.getBoardConfigIdx());
        int isCategoryExist = boardCategoryConfigMapper.isCategoryExist(boardInsertDTO.getBoardCategoryConfigIdx());

        if (isBoardConfigExist == 0) {
            throw new BoardConfigException(ErrorCode.isBoardConfigNotFoundException);
        }

        if (isCategoryExist == 0) {
            throw new BoardCategoryConfigException(ErrorCode.isBoardCategoryConfigNotFoundException);
        }

        boardInsertDTO.setUserIdx(getUserIdx());
        Board board = modelMapper.map(boardInsertDTO, Board.class);
        boardMapper.insertBoard(board);
        fileService.fileUpload(boardInsertDTO.getFiles(), boardInsertDTO.getTargetType(), "board", board.getIdx());

        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();
        boardMapper.updateBoardViewCountByIdx(idx);
        result.setResponseData("board", boardMapper.findOneBoardByIdx(idx));
        result.setResponseData("boardFiles", boardMapper.findAllFilesByBoard(idx));
        return result;
    }

    public ApiResponse update(BoardDTO.BoardUpdateDTO boardUpdateDTO) {
        ResponseMap result = new ResponseMap();

        int isBoardConfigExist = boardConfigMapper.isBoardConfigExist(boardUpdateDTO.getBoardConfigIdx());
        int isCategoryExist = boardCategoryConfigMapper.isCategoryExist(boardUpdateDTO.getBoardCategoryConfigIdx());

        if (isBoardConfigExist == 0) {
            throw new BoardConfigException(ErrorCode.isBoardConfigNotFoundException);
        }

        if (isCategoryExist == 0) {
            throw new BoardCategoryConfigException(ErrorCode.isBoardCategoryConfigNotFoundException);
        }

        Board board = modelMapper.map(boardUpdateDTO, Board.class);
        boardMapper.updateBoardByIdx(board);
        fileService.fileUpdate(boardUpdateDTO.getFiles(), "board", board.getIdx());
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();

        fileService.fileDelete(idx, "board");
        boardMapper.deleteBoardByIdx(idx);
        return result;
    }

}
