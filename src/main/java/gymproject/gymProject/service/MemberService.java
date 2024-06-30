package gymproject.gymProject.service;

import gymproject.gymProject.entity.Form.MemberForm;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.address.Address;
import gymproject.gymProject.entity.exception.DuplicatePasswordException;
import gymproject.gymProject.repogitory.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long createMember(Member member){

        Optional<Member> findByMember = memberRepository.findByEmail(member.getPassword());

        if(findByMember.isPresent()){
            throw new DuplicatePasswordException("Member already exists."); //회원 가입 검증 로직
        }

        //회원 가입
        memberRepository.save(member);

        return member.getId();
    }







    public Member alertMember(MemberForm memberForm){

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
        return new Member(memberForm.getUsername(), memberForm.getPassword(), memberForm.getEmail(), memberForm.getPhoneNumber(), memberForm.getRole(), address);
    }





}
