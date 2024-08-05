package com.smile.wanted_pre_task.job_post.controller;


import com.smile.wanted_pre_task.global.exception.ResponseMessage;
import com.smile.wanted_pre_task.job_post.dto.*;
import com.smile.wanted_pre_task.job_post.service.JobPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobPosting")
@Slf4j
public class JobPostController {

    private final JobPostService jobPostService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<JobPostDto.Response> posting(@Validated @RequestBody JobPostDto.Post jobPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobPostService.posting(jobPost));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<JobPostDto.Update> updatePost(@RequestParam Long postId, @Validated @RequestBody JobPostDto.Update jobPostUpdateReqDto) {
        return ResponseEntity.ok(jobPostService.updatePost(postId, jobPostUpdateReqDto));
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePost(@RequestParam Long postId) {
        jobPostService.deletePost(postId);
        return ResponseEntity.ok(ResponseMessage.DELETE_SUCCESS);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<JobPostDto.Response>> listing() {
        return ResponseEntity.ok(jobPostService.listing());
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<JobPostDto.Response>> search(@RequestParam String query, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(jobPostService.search(query, pageable));
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseEntity<JobPostDetail> getDetail(@RequestParam Long postId) {
        return ResponseEntity.ok(jobPostService.getDetail(postId));
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public ResponseEntity<JobPostDto.Apply> apply(@Validated @RequestBody JobPostDto.Apply apply) {
        return ResponseEntity.ok(jobPostService.apply(apply.getPostId(), apply.getMemberId()));
    }

}