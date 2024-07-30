package com.smile.wanted_pre_task.job_post.controller;


import com.smile.wanted_pre_task.job_post.dto.JobPostCommand;
import com.smile.wanted_pre_task.job_post.dto.JobPostUpdateReqDto;
import com.smile.wanted_pre_task.job_post.service.JobPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobPosting")
@Slf4j
public class JobPostController {

    private final JobPostService jobPostService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void posting(@RequestBody JobPostCommand jobPostCommand) {
        jobPostService.posting(jobPostCommand);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> listing() {
        return ResponseEntity.ok(jobPostService.listing());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updatePost(@RequestParam Long postId, @RequestBody JobPostUpdateReqDto jobPostUpdateReqDto) {
        return ResponseEntity.ok(jobPostService.updatePost(postId, jobPostUpdateReqDto));
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public void deletePost(@RequestParam Long postId) {
        jobPostService.deletePost(postId);
    }

}