package com.smile.wanted_pre_task.job_post.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smile.wanted_pre_task.company.domain.Company;
import com.smile.wanted_pre_task.company.repository.CompanyRepository;
import com.smile.wanted_pre_task.global.ResponseMessage;
import com.smile.wanted_pre_task.job_post.domain.JobPost;
import com.smile.wanted_pre_task.job_post.dto.*;
import com.smile.wanted_pre_task.job_post.repository.JobPostRepository;
import com.smile.wanted_pre_task.member.domain.Member;
import com.smile.wanted_pre_task.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.smile.wanted_pre_task.company.domain.QCompany.company;
import static com.smile.wanted_pre_task.job_post.domain.QJobPost.jobPost;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobPostServiceImpl implements JobPostService {

    private final JobPostRepository jobPostRepository;
    private final CompanyRepository companyRepository;
    private final MemberRepository memberRepository;
    private final EntityManager em;

    /**
     * 1. 채용 공고 등록
     *
     * @param postDto
     */
    @Transactional
    @Override
    public JobPostDto.Response posting(JobPostDto.Post postDto) {
        // 1. 회사 ID 검증 (게시글이 등록 가능한 회사인지 유효성 체크) - 토큰이 없으니 회사Id를 함께 전달
        Company company = getCompany(postDto.getCompanyId());
        JobPost jobPost = postDto.toRegisterEntity(company);
        // 2. DB 저장
        jobPostRepository.save(jobPost);
        return convertToDto(jobPost);
    }

    /**
     * 2. 채용 공고 수정
     *
     * @param postId
     * @param jobPostUpdateReqDto
     * @return
     */
    @Transactional
    @Override
    public JobPostDto.Update updatePost(Long postId, JobPostDto.Update jobPostUpdateReqDto) {
        // 유효성 체크
        JobPost jobPost = getJobPost(postId);

        JobPost initJobPost = jobPostUpdateReqDto.toUpdateEntity();
        jobPost.update(initJobPost);

        return new JobPostDto.Update(jobPost);
    }

    /**
     * 3. 채용 공고 삭제
     *
     * @param postId
     */
    @Transactional
    @Override
    public void deletePost(Long postId) {
        jobPostRepository.deleteById(postId);
    }

    /**
     * 4-1. 채용 공고 목록 조회
     *
     * @return
     */
    @Transactional
    @Override
    public List<JobPostDto.Response> listing() {
        List<JobPost> jobPosts = jobPostRepository.findAllByOrderByCreatedAtDesc();
        return jobPosts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * 4-2. 채용 공고 검색
     *
     * @param query
     * @param pageable
     * @return
     */
    @Transactional
    @Override
    public List<JobPostDto.Response> search(String query, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        BooleanBuilder searchConditions = new BooleanBuilder();

        // 검색 조건
        if (query != null) {
            searchConditions.or(company.companyName.contains(query))
                    .or(jobPost.position.contains(query))
                    .or(jobPost.stack.contains(query));
        }

        // 기본 정렬
        List<JobPost> jobPosts = queryFactory.selectFrom(jobPost)
                .where(searchConditions)
                .orderBy(jobPost.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 원하는 형태의 List 타입으로 변경하여 return
        return jobPosts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * 5. 채용 공고 상세 조회
     *
     * @param postId
     * @return
     */
    @Transactional
    @Override
    public JobPostDetail getDetail(Long postId) {
        JobPost jobPost = getJobPost(postId);
        List<JobPost> jobPosts = jobPostRepository.findAllByCompanyCompanyIdOrderByCreatedAtDesc(jobPost.getCompany().getCompanyId());

        // 해당 회사가 모집 중인 그 외 모든 공고 함께 조회 (현재 조회 중인 공고는 제외)
        List<JobPostDetailList> jobPostDetailLists = jobPosts.stream()
                .filter(post -> !post.getPostId().equals(postId))
                .map(this::convertToDetailDto)
                .collect(Collectors.toList());

        return new JobPostDetail(jobPost, jobPostDetailLists);
    }

    /**
     * 6. 채용 공고 지원
     *
     * @param postId
     * @param memberId
     * @return
     */
    @Transactional
    @Override
    public JobPostDto.Apply apply(Long postId, Long memberId) {
        // 유저 체크 (존재하는 유저인지)
        Member member = getMember(memberId);
        // post에 memberId 저장
        JobPost jobPost = getJobPost(postId);

        if (member.getJobPost() != null && member.getJobPost().getPostId().equals(postId)) {
            throw new IllegalArgumentException("이미 지원한 채용 공고 입니다.");
        }

        jobPost.addMember(member);
        jobPostRepository.save(jobPost);
        return new JobPostDto.Apply(postId, memberId);
    }

    private JobPostDetailList convertToDetailDto(JobPost jobPost) {
        return new JobPostDetailList(
                jobPost.getPostId(),
                jobPost.getPosition(),
                jobPost.getCompany().getRegion()
        );
    }


    private JobPostDto.Response convertToDto(JobPost jobPost) {
        return new JobPostDto.Response(
                jobPost.getPostId(),
                jobPost.getCompany().getCompanyName(),
                jobPost.getCompany().getNation(),
                jobPost.getCompany().getRegion(),
                jobPost.getPosition(),
                jobPost.getReward(),
                jobPost.getStack(),
                jobPost.getCreatedAt(),
                jobPost.getUpdatedAt()
        );
    }

    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NoSuchElementException(ResponseMessage.MEMBER_NOT_FOUND)
        );
    }

    private JobPost getJobPost(Long postId) {
        return jobPostRepository.findById(postId).orElseThrow(
                () -> new NoSuchElementException(ResponseMessage.JOBPOST_NOT_FOUND)
        );
    }

    public Company getCompany(long companyId) {
        return companyRepository.findById(companyId).orElseThrow(
                () -> new NoSuchElementException(ResponseMessage.COMPANY_NOT_FOUND)
        );
    }
}
