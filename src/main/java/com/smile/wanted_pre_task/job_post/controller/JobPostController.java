package com.smile.wanted_pre_task.job_post.controller;


import com.smile.wanted_pre_task.job_post.dto.*;
import com.smile.wanted_pre_task.job_post.service.JobPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobPosting")
@Slf4j
public class JobPostController {

    private final JobPostService jobPostService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void posting(@RequestBody JobPostDto.Post jobPost) {
        jobPostService.posting(jobPost);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<JobPostDto.Response>> listing() {
        return ResponseEntity.ok(jobPostService.listing());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<JobPostDto.Update> updatePost(@RequestParam Long postId, @RequestBody JobPostDto.Update jobPostUpdateReqDto) {
        return ResponseEntity.ok(jobPostService.updatePost(postId, jobPostUpdateReqDto));
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public void deletePost(@RequestParam Long postId) {
        jobPostService.deletePost(postId);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseEntity<JobPostDetail> getDetail(@RequestParam Long postId) {
        return ResponseEntity.ok(jobPostService.getDetail(postId));
    }

}