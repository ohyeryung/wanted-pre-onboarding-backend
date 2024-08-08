package com.smile.wanted_pre_task.company.controller;

import com.smile.wanted_pre_task.company.dto.CompanyDto;
import com.smile.wanted_pre_task.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public void enrollCompany(@RequestBody CompanyDto.Post post) {
        companyService.enroll(post);
    }
}
