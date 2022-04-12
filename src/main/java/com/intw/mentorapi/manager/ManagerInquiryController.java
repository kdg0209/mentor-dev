package com.intw.mentorapi.manager;

import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.company.CompanyDTO;
import com.intw.mentorapi.dto.inquiry.InquiryDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.InquiryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Inquiry / 문의 ")
@RequestMapping("/manager/inquiry")
@RestController
@RequiredArgsConstructor
public class ManagerInquiryController {

    private final InquiryService inquiryService;

    @GetMapping
    @ApiOperation(value="문의 목록")
    public ApiResponse index(PageDTO pageDTO) {
        return inquiryService.lists(pageDTO);
    }

    @PostMapping
    @ApiOperation(value="문의 작성")
    public ApiResponse write(@RequestBody @Valid InquiryDTO.InquiryInsertDTO inquiryInsertDTO) {
        return inquiryService.managerWrite(inquiryInsertDTO);
    }

    @GetMapping("/{idx}")
    @ApiOperation(value="문의 조회")
    public ApiResponse view(@PathVariable("idx") long idx) {
        return inquiryService.view(idx);
    }

    @PutMapping
    @ApiOperation(value="문의 수정")
    public ApiResponse update(@RequestBody @Valid InquiryDTO.InquiryUpdateDTO inquiryUpdateDTO) {
        return inquiryService.managerUpdate(inquiryUpdateDTO);
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value="문의 삭제")
    public ApiResponse delete(@PathVariable("idx") long idx) {
        return inquiryService.delete(idx);
    }
}
