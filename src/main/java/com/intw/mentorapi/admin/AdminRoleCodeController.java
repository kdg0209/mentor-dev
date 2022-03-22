package com.intw.mentorapi.admin;

import com.intw.mentorapi.dto.roleCode.RoleCodeDTO;
import com.intw.mentorapi.response.ApiResponse;
import com.intw.mentorapi.service.RoleCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Role Code / 권한 설정")
@RequestMapping("/admin/role-code")
@RestController
@RequiredArgsConstructor
public class AdminRoleCodeController {

    private final RoleCodeService roleCodeService;

    @GetMapping
    @ApiOperation(value="권한 설정 목록")
    public ApiResponse index() {
        return roleCodeService.lists();
    }

    @PostMapping
    @ApiOperation(value="권한 설정 작성")
    public ApiResponse write(@RequestBody @Valid RoleCodeDTO roleCodeDTO) {
        return roleCodeService.write(roleCodeDTO);
    }
}
