package com.intw.mentorapi.manager;

import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.inquiry.InquiryDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.InquiryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Inquiry Reply / 문의 답변")
@RequestMapping("/manager/inquiry-reply")
@RestController
@RequiredArgsConstructor
public class ManagerInquiryController {

    private final InquiryService inquiryService;

    @GetMapping("/{idx}")
    @ApiOperation(value="문의 목록")
    public ApiResponse index(PageDTO pageDTO, @PathVariable("idx") long idx) {
        return inquiryService.managerLists(pageDTO, idx);
    }

    @PostMapping
    @ApiOperation(value="문의 작성")
    public ApiResponse write(@RequestBody @Valid InquiryDTO.InquiryInsertDTO inquiryInsertDTO) {
        return inquiryService.managerWrite(inquiryInsertDTO);
    }

}
