package gymproject.gymProject.service;

import gymproject.gymProject.entity.Dto.Form.ReviewForm;
import gymproject.gymProject.entity.Review;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private GymService gymService;

    @Autowired
    private EntityManager em;


    @Test
    public void 리뷰가입테스트() throws Exception {
       //given
        MultipartFile multipartFile = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test".getBytes());

        ReviewForm reviewForm = new ReviewForm("좋네요", 3, multipartFile);

        //when
        Long saveId = reviewService.saveReview(1L, 1L, reviewForm);

        em.flush();
        em.clear();

        Review findReview = reviewService.findReviewById(saveId);



        //then

        Assertions.assertThat(findReview.getContent()).isEqualTo("좋네요");
        Assertions.assertThat(findReview.getMember().getId()).isEqualTo(1L);

        Assertions.assertThat(findReview.getMember().getName()).isEqualTo("이성원");

    }





}