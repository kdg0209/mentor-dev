package com.intw.mentorapi.service;

import com.intw.mentorapi.dao.Board;
import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.board.BoardDTO;
import com.intw.mentorapi.exception.ErrorCode;
import com.intw.mentorapi.exception.customException.BoardCategoryConfigException;
import com.intw.mentorapi.exception.customException.BoardConfigException;
import com.intw.mentorapi.exception.customException.BoardException;
import com.intw.mentorapi.handler.UserProvider;
import com.intw.mentorapi.mapper.BoardCategoryConfigMapper;
import com.intw.mentorapi.mapper.BoardConfigMapper;
import com.intw.mentorapi.mapper.BoardMapper;
import com.intw.mentorapi.mapper.CommentMapper;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.response.ResponseMap;
import com.intw.mentorapi.status.RoleStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService extends UserProvider {

    private final BoardMapper boardMapper;
    private final CommentMapper commentMapper;
    private final BoardCategoryConfigMapper boardCategoryConfigMapper;
    private final BoardConfigMapper boardConfigMapper;
    private final FileService fileService;

    public ApiResponse lists(PageDTO pageDTO) {
        ResponseMap result = new ResponseMap();

        if (getUser() != null) {
            result.setResponseData("boardList", boardMapper.findAllBoard(pageDTO, getUser().getRole()));
        } else {
            result.setResponseData("boardList", boardMapper.findAllBoard(pageDTO, RoleStatus.ROLE_ALL));
        }

        return result;
    }

    public ApiResponse write(BoardDTO.BoardInsertDTO params) {
        ResponseMap result = new ResponseMap();

        int isBoardConfigExist = boardConfigMapper.isBoardConfigExist(params.getBoardConfigIdx());
        int isCategoryExist = boardCategoryConfigMapper.isCategoryExist(params.getBoardCategoryConfigIdx());

        if (isBoardConfigExist == 0) {
            throw new BoardConfigException(ErrorCode.isBoardConfigNotFoundException);
        }

        if (isCategoryExist == 0) {
            throw new BoardCategoryConfigException(ErrorCode.isBoardCategoryConfigNotFoundException);
        }

        Board board = Board.builder()
                        .boardConfigIdx(params.getBoardConfigIdx())
                        .boardCategoryConfigIdx(params.getBoardCategoryConfigIdx())
                        .userIdx(getUser().getIdx())
                        .status(params.getStatus())
                        .title(params.getTitle())
                        .contents(params.getContents())
                        .build();
        boardMapper.insertBoard(board);
        fileService.fileUpload(params.getFiles(), params.getTargetType(), "board", board.getIdx());
        return result;
    }

    public ApiResponse view(long idx) {
        ResponseMap result = new ResponseMap();

        if (getUser() != null) {
            result.setResponseData("board", boardMapper.findOneBoard(idx, getUser().getRole()));
        } else {
            result.setResponseData("board", boardMapper.findOneBoard(idx, RoleStatus.ROLE_ALL));
        }

        boardMapper.updateBoardViewCount(idx);
        result.setResponseData("boardFiles", boardMapper.findAllFilesByBoard(idx));
        result.setResponseData("commentList", commentMapper.findAllCommentByBoard(idx));
        return result;
    }

    public ApiResponse update(BoardDTO.BoardUpdateDTO params) {
        ResponseMap result = new ResponseMap();

        int isBoardExist = boardMapper.isBoardExist(params.getIdx());
        int isBoardConfigExist = boardConfigMapper.isBoardConfigExist(params.getBoardConfigIdx());
        int isCategoryExist = boardCategoryConfigMapper.isCategoryExist(params.getBoardCategoryConfigIdx());

        if (isBoardExist == 0) {
            throw new BoardException(ErrorCode.isBoardNotFoundException);
        }

        if (isBoardConfigExist == 0) {
            throw new BoardConfigException(ErrorCode.isBoardConfigNotFoundException);
        }

        if (isCategoryExist == 0) {
            throw new BoardCategoryConfigException(ErrorCode.isBoardCategoryConfigNotFoundException);
        }

        Board board = Board.builder()
                        .idx(params.getIdx())
                        .boardConfigIdx(params.getBoardConfigIdx())
                        .boardCategoryConfigIdx(params.getBoardCategoryConfigIdx())
                        .status(params.getStatus())
                        .title(params.getTitle())
                        .contents(params.getContents())
                        .build();

        boardMapper.updateBoard(board);
        return result;
    }

    public ApiResponse delete(long idx) {
        ResponseMap result = new ResponseMap();

        fileService.fileDelete(idx, "board");
        boardMapper.deleteBoard(idx);
        return result;
    }
}
