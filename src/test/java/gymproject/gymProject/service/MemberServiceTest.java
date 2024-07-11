package gymproject.gymProject.service;

import gymproject.gymProject.entity.Enum.Role;
import gymproject.gymProject.entity.Form.MemberForm;
import gymproject.gymProject.entity.Form.MemberModifyForm;
import gymproject.gymProject.entity.Likes;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.Review;
import gymproject.gymProject.entity.exception.DuplicatePasswordException;
import gymproject.gymProject.repogitory.MemberRepository;
import jakarta.persistence.AssociationOverride;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        MemberForm memberForm = makeForm();

        Member member = memberService.alertMember(memberForm);

        //when
        Long id = memberService.createMember(member);

        entityManager.flush();
        entityManager.clear();


        Optional<Member> findMember = memberRepository.findById(id);

        //then

        Assertions.assertThat(member.getId()).isEqualTo(findMember.get().getId());



    }

    @Test
    public void 좋아요테스트() throws Exception {
       //given
        MemberForm memberForm = makeForm();

        // Save member
        Member member = memberService.alertMember(memberForm);
        memberService.createMember(member);
        entityManager.flush();
        entityManager.clear();

        // Save review
        Review review = new Review("ㅋㅋ", 3, member);
        entityManager.persist(review);
        entityManager.flush();
        entityManager.clear();


        Likes likes= new Likes(member, review);
        entityManager.persist(likes);
        entityManager.flush();
        entityManager.clear();

        //when


       //then
    }

    @Test
    public void 회원가입유효성검사() throws Exception {
       //given

        MemberForm memberForm1 = makeForm();
        MemberForm memberForm2 = makeForm();

        Member member1 = memberService.alertMember(memberForm1);
        Member member2 = memberService.alertMember(memberForm2);

        //when
        Long id = memberService.createMember(member1);


       //then

        Assertions.assertThatThrownBy(() -> memberService.createMember(member2)).isInstanceOf(DuplicatePasswordException.class);
    }

    @Test
    public void 수정하기() throws Exception {
       //given

        MemberForm memberForm1 = makeForm();

        Member member1 = memberService.alertMember(memberForm1);

        //when
        Long id = memberService.createMember(member1);

        entityManager.flush();
        entityManager.clear();

       //when

        MemberModifyForm memberModifyForm = new MemberModifyForm("리중딱","010-1561-1611", "전주시", "배학2길", "303호" );

        memberService.modify(id, memberModifyForm);
        entityManager.flush();
        entityManager.clear();

        Member findMember = memberService.findMemberById(id);




        //then
        Assertions.assertThat(findMember.getName()).isEqualTo("리중딱");
    }

    private static MemberForm makeForm() {
        MemberForm memberForm = new MemberForm("성원", "12345","이성원", "k1200@nate.com", "010-7119-8112", "광양", "광장로", "84", Role.user);
        return memberForm;
    }


}