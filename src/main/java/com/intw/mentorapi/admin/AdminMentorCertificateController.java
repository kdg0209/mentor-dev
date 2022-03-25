package com.intw.mentorapi.admin;


import com.intw.mentorapi.dto.mentorCertificate.MentorCertificateDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.MentorCertificateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Mentor Certificate / 멘토 자격증")
@RequestMapping("/admin/mentor-certificate")
@RestController
@RequiredArgsConstructor
public class AdminMentorCertificateController {

    private final MentorCertificateService mentorCertificateService;


    @PostMapping
    @ApiOperation(value="멘토 자격증 등록")
    public ApiResponse write(@RequestBody @Valid MentorCertificateDTO.MentorCertificateInsertDTO mentorCertificateInsertDTO) {
        return mentorCertificateService.write(mentorCertificateInsertDTO);
    }

    @GetMapping("/{idx}")
    @ApiOperation(value="멘토 자격증 조회")
    public ApiResponse view(@PathVariable("idx") long idx) {
        return mentorCertificateService.view(idx);
    }

    @PutMapping
    @ApiOperation(value="멘토 자격증 수정")
    public ApiResponse update(@RequestBody @Valid MentorCertificateDTO.MentorCertificateUpdateDTO mentorCertificateUpdateDTO) {
        return mentorCertificateService.update(mentorCertificateUpdateDTO);
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value="멘토 자격증 삭제")
    public ApiResponse delete(@PathVariable("idx") long idx) {
        return mentorCertificateService.delete(idx);
    }
}
