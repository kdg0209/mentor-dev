package com.intw.mentorapi.manager;

import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.inquiryReply.InquiryReplyDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.InquiryReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Inquiry Reply / 문의 답변")
@RequestMapping("/manager/inquiry-reply")
@RestController
@RequiredArgsConstructor
public class ManagerInquiryReplyController {

    private final InquiryReplyService inquiryReplyService;

    @PostMapping
    @ApiOperation(value="문의 답변 작성")
    public ApiResponse write(@RequestBody @Valid InquiryReplyDTO.InquiryReplyInsertDTO inquiryReplyInsertDTO) {
        return inquiryReplyService.write(inquiryReplyInsertDTO);
    }

    @PutMapping
    @ApiOperation(value="문의 답변 수정")
    public ApiResponse update(@RequestBody @Valid InquiryReplyDTO.InquiryReplyUpdateDTO inquiryReplyUpdateDTO) {
        return inquiryReplyService.update(inquiryReplyUpdateDTO);
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value="문의 답변 삭제")
    public ApiResponse delete(@PathVariable("idx") long idx) {
        return inquiryReplyService.delete(idx);
    }
}
