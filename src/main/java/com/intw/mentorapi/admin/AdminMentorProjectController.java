package com.intw.mentorapi.admin;

import com.intw.mentorapi.dto.mentorCareer.MentorCareerDTO;
import com.intw.mentorapi.dto.mentorProject.MentorProjectDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.MentorProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Mentor Project / 멘토 프로젝트")
@RequestMapping("/admin/mentor-project")
@RestController
@RequiredArgsConstructor
public class AdminMentorProjectController {

    private final MentorProjectService mentorProjectService;

    @PostMapping
    @ApiOperation(value="멘토 프로젝트 등록")
    public ApiResponse write(@RequestBody @Valid MentorProjectDTO.MentorProjectInsertDTO mentorProjectInsertDTO) {
        return mentorProjectService.write(mentorProjectInsertDTO);
    }

    @GetMapping("/{idx}")
    @ApiOperation(value="멘토 프로젝트 조회")
    public ApiResponse view(@PathVariable("idx") long idx) {
        return mentorProjectService.view(idx);
    }


    @PutMapping
    @ApiOperation(value="멘토 프로젝트 수정")
    public ApiResponse update(@RequestBody @Valid MentorProjectDTO.MentorProjectUpdateDTO mentorProjectUpdateDTO) {
        return mentorProjectService.update(mentorProjectUpdateDTO);
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value="멘토 프로젝트 삭제")
    public ApiResponse delete(@PathVariable("idx") long idx) {
        return mentorProjectService.delete(idx);
    }

}
