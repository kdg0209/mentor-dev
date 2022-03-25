package com.intw.mentorapi.admin;

import com.intw.mentorapi.dto.mentorCareer.MentorCareerDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.MentorCareerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Mentor Career / 멘토 경력")
@RequestMapping("/admin/mentor-career")
@RestController
@RequiredArgsConstructor
public class AdminMentorCareerController {

    private final MentorCareerService mentorCareerService;

    @PostMapping
    @ApiOperation(value="멘토 경력 등록")
    public ApiResponse write(@RequestBody @Valid MentorCareerDTO.MentorCareerInsertDTO mentorCareerInsertDTO) {
        return mentorCareerService.write(mentorCareerInsertDTO);
    }

    @GetMapping("/{idx}")
    @ApiOperation(value="멘토 경력 조회")
    public ApiResponse view(@PathVariable("idx") long idx) {
        return mentorCareerService.view(idx);
    }

    @PutMapping
    @ApiOperation(value="멘토 경력 수정")
    public ApiResponse update(@RequestBody @Valid MentorCareerDTO.MentorCareerUpdateDTO mentorCareerUpdateDTO) {
        return mentorCareerService.update(mentorCareerUpdateDTO);
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value="멘토 경력 삭제")
    public ApiResponse delete(@PathVariable("idx") long idx) {
        return mentorCareerService.delete(idx);
    }
}
