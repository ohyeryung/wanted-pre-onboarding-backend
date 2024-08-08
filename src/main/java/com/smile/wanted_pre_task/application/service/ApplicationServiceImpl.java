package com.smile.wanted_pre_task.application.service;

import com.smile.wanted_pre_task.application.domain.Application;
import com.smile.wanted_pre_task.application.dto.ApplicationDto;
import com.smile.wanted_pre_task.application.repository.ApplicationRepository;
import com.smile.wanted_pre_task.global.exception.AlreadyAppliedException;
import com.smile.wanted_pre_task.job_post.domain.JobPost;
import com.smile.wanted_pre_task.member.domain.Member;
import com.smile.wanted_pre_task.util.service.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final UtilService utilService;
    private final ApplicationRepository applicationRepository;

    /**
     * 6. 채용 공고 지원
     *
     * @param applicationDto
     * @return
     */
    @Transactional
    @Override
    public ApplicationDto apply(ApplicationDto applicationDto) {
        Long postId = applicationDto.getPostId();
        UUID memberId = applicationDto.getMemberId();
        // 채용 공고 유효성 체크
        JobPost jobPost = utilService.getJobPost(postId);
        // 유저 유효성 체크 (존재하는 유저인지)
        Member member = utilService.getMember(memberId);

        if (applicationRepository.findByJobPostAndMember(jobPost, member).isPresent()) throw new AlreadyAppliedException();

        Application application = new Application(null, jobPost, member);
        applicationRepository.save(application);

        return new ApplicationDto(postId, memberId);
    }

}
