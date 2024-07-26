package gymproject.gymProject.service;

import gymproject.gymProject.File.FileStore;
import gymproject.gymProject.File.ReviewFileStore;
import gymproject.gymProject.File.UploadFile;
import gymproject.gymProject.entity.Dto.Form.ProfileForm;
import gymproject.gymProject.entity.Dto.Form.ReviewForm;
import gymproject.gymProject.entity.Gym;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.Review;
import gymproject.gymProject.entity.exception.GymNotFoundException;
import gymproject.gymProject.entity.exception.MemberNotFoundException;
import gymproject.gymProject.repogitory.GymRepository;
import gymproject.gymProject.repogitory.MemberRepository;
import gymproject.gymProject.repogitory.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final GymRepository gymRepository;
    private final ReviewFileStore reviewFileStore;

        @Transactional // 리뷰 저장
        public Long saveReview(Long memberId, Long gymId, ReviewForm reviewForm) throws IOException {
            Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException("해당 멤버는 없습니다"));
            Gym findGym = gymRepository.findById(gymId).orElseThrow(() -> new GymNotFoundException("해당 헬스장은 없습니다"));
            UploadFile uploadFile = alterUploadFile(reviewForm);

            Review review = new Review(reviewForm.getContent(), reviewForm.getRating(), uploadFile, findMember, findGym);

            Review saveReview = reviewRepository.save(review);

            return saveReview.getId();
        }

        public Review findReviewById(Long reviewId) {
            return reviewRepository.findById(reviewId).orElseThrow(()-> new RuntimeException("리뷰 없음"));
        }


    private UploadFile alterUploadFile(ReviewForm reviewForm) throws IOException {
        UploadFile uploadFile = reviewFileStore.storeFile(reviewForm.getMultipartFile());
        return uploadFile;
    }




}
