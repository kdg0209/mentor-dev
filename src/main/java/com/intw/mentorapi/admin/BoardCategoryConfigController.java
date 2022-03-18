package com.intw.mentorapi.admin;

import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.boardCategoryConfig.BoardCategoryConfigDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.BoardCategoryConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Board Category Config / 게시판 카테고리 설정")
@RequestMapping("/admin/board-category-config")
@RestController
@RequiredArgsConstructor
public class BoardCategoryConfigController {

    private final BoardCategoryConfigService boardCategoryConfigService;

    @GetMapping("/index")
    @ApiOperation(value="게시판 카테고리 설정 목록")
    public ApiResponse index(@Valid PageDTO pageDTO) {
        return boardCategoryConfigService.lists(pageDTO);
    }

    @PostMapping
    @ApiOperation(value="게시판 카테고리 설정 작성")
    public ApiResponse write(@RequestBody @Valid BoardCategoryConfigDTO.BoardCategoryConfigInsertDTO boardCategoryConfigInsertDTO) {
        return boardCategoryConfigService.write(boardCategoryConfigInsertDTO);
    }


    @GetMapping("/view/{idx}")
    @ApiOperation(value="게시판 카테고리 설정 조회")
    public ApiResponse view(@PathVariable("idx") long idx) {
        return boardCategoryConfigService.view(idx);
    }

    @PutMapping
    @ApiOperation(value="게시판 카테고리 설정 수정")
    public ApiResponse update(@RequestBody @Valid BoardCategoryConfigDTO.BoardCategoryConfigUpdateDTO boardCategoryConfigUpdateDTO) {
        return boardCategoryConfigService.update(boardCategoryConfigUpdateDTO);
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value="게시판 카테고리 설정 삭제")
    public ApiResponse delete(@PathVariable("idx") long idx) {
        return boardCategoryConfigService.delete(idx);
    }
}
