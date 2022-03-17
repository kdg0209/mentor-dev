package com.intw.mentorapi.admin;

import com.intw.mentorapi.dto.PageDTO;
import com.intw.mentorapi.dto.company.CompanyDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Company / 기업")
@RequestMapping("/admin/company")
@RestController
@RequiredArgsConstructor
public class AdminCompanyController {

    private final CompanyService companyService;

    @GetMapping("/lists")
    @ApiOperation(value="기업 계정 목록")
    public ApiResponse lists(@Valid PageDTO pageDTO) {
        return companyService.lists(pageDTO);
    }

    @PostMapping
    @ApiOperation(value="기업 계정 작성")
    public ApiResponse write(@RequestBody @Valid CompanyDTO.CompanyInsertDTO companyInsertDTO) {
        return companyService.write(companyInsertDTO);
    }

    @GetMapping("/view/{idx}")
    @ApiOperation(value="기업 계정 조회")
    public ApiResponse view(@PathVariable("idx") int idx) {
        return companyService.view(idx);
    }

    @PutMapping
    @ApiOperation(value="기업 계정 수정")
    public ApiResponse update(@RequestBody @Valid CompanyDTO.CompanyUpdateDTO companyUpdateDTO) {
        return companyService.update(companyUpdateDTO);
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value="기업 계정 삭제")
    public ApiResponse delete(@PathVariable("idx") Integer idx) {
        return companyService.delete(idx);
    }

}
