package com.smile.wanted_pre_task.application.controller;

import com.smile.wanted_pre_task.application.dto.ApplicationDto;
import com.smile.wanted_pre_task.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobPosting")
public class ApplicationController {

    private final ApplicationService applicationService;

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public ResponseEntity<ApplicationDto> apply(@Validated @RequestBody ApplicationDto applicationDto) {
        return ResponseEntity.ok(applicationService.apply(applicationDto));
    }
}
