package com.intw.mentorapi.admin;

import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.mentor.MentorDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.MentorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Mentor / 멘토")
@RequestMapping("/admin/mentor")
@RestController
@RequiredArgsConstructor
public class AdminMentorController {

    private final MentorService mentorService;

    @GetMapping
    @ApiOperation(value="멘토 목록")
    public ApiResponse index(@Valid PageDTO pageDTO) {
        return mentorService.lists(pageDTO);
    }

    @PostMapping
    @ApiOperation(value="멘토 등록")
    public ApiResponse write(@RequestBody @Valid MentorDTO.MentorInsertDTO mentorInsertDTO) {
        return mentorService.write(mentorInsertDTO);
    }

    @GetMapping("/{idx}")
    @ApiOperation(value="멘토 조회")
    public ApiResponse view(@PathVariable("idx") long idx) {
        return mentorService.view(idx);
    }

    @PutMapping
    @ApiOperation(value="멘토 수정")
    public ApiResponse update(@RequestBody @Valid MentorDTO.MentorUpdateDTO mentorUpdateDTO) {
        return mentorService.update(mentorUpdateDTO);
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value="멘토 삭제")
    public ApiResponse delete(@PathVariable("idx") long idx) {
        return mentorService.delete(idx);
    }
}
