package com.intw.mentorapi.admin;

import com.intw.mentorapi.dto.mentorCategoryConfig.MentorCategoryConfigDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.MentorCategoryConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Mentor Category Config / 멘토 카테고리 설정")
@RequestMapping("/admin/mentor-category-config")
@RestController
@RequiredArgsConstructor
public class AdminMentorCategoryConfigController {

    private final MentorCategoryConfigService mentorCategoryConfigService;

    @GetMapping
    @ApiOperation(value="멘토 카테고리 목록")
    public ApiResponse index() {
        return mentorCategoryConfigService.lists();
    }

    @PostMapping
    @ApiOperation(value="멘토 카테고리 등록")
    public ApiResponse write(@RequestBody @Valid MentorCategoryConfigDTO.MentorCategoryConfigInsertDTO mentorCategoryConfigInsertDTO) {
        return mentorCategoryConfigService.write(mentorCategoryConfigInsertDTO);
    }

    @GetMapping("/{idx}")
    @ApiOperation(value="멘토 카테고리 조회")
    public ApiResponse view(@PathVariable("idx") long idx) {
        return mentorCategoryConfigService.view(idx);
    }

    @PutMapping
    @ApiOperation(value="멘토 카테고리 수정")
    public ApiResponse update(@RequestBody @Valid MentorCategoryConfigDTO.MentorCategoryConfigUpdateDTO mentorCategoryConfigUpdateDTO) {
        return mentorCategoryConfigService.update(mentorCategoryConfigUpdateDTO);
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value="멘토 카테고리 삭제")
    public ApiResponse delete(@PathVariable("idx") long idx) {
        return mentorCategoryConfigService.delete(idx);
    }
}
