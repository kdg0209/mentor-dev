package com.intw.mentorapi.admin;

import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.InquiryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Inquiry / 문의")
@RequestMapping("/admin/inquiry")
@RestController
@RequiredArgsConstructor
public class AdminInquiryController {

    private final InquiryService inquiryService;

    @GetMapping
    @ApiOperation(value="문의 목록")
    public ApiResponse index(PageDTO pageDTO) {
        return inquiryService.lists(pageDTO);
    }

    @GetMapping("/{idx}")
    @ApiOperation(value="문의 조회")
    public ApiResponse view(@PathVariable("idx") long idx) {
        return inquiryService.view(idx);
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value="문의 삭제")
    public ApiResponse delete(@PathVariable("idx") long idx) {
        return inquiryService.delete(idx);
    }
}
