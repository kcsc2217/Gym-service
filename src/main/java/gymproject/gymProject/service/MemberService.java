package gymproject.gymProject.service;

import gymproject.gymProject.entity.Form.MemberForm;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.address.Address;
import gymproject.gymProject.entity.exception.DuplicatePasswordException;
import gymproject.gymProject.repogitory.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long createMember(Member member){  //회원 가입

        Optional<Member> findByMember = memberRepository.findByEmailAndUsername(member.getEmail(), member.getUsername());

        validationFindMember(findByMember);

        encodePassword(member);
        //회원 가입
        memberRepository.save(member);

        return member.getId();
    }



    public void login(Member member){


    }

    private static void validationFindMember(Optional<Member> findByMember) {
        if(findByMember.isPresent()){
            throw new DuplicatePasswordException("Member already exists."); //회원 가입 검증 로직
        }
    }

    private void encodePassword(Member member){
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
    }



    public Member alertMember(MemberForm memberForm){

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
        return new Member(memberForm.getUsername(), memberForm.getPassword(), memberForm.getEmail(), memberForm.getPhoneNumber(), memberForm.getRole(), memberForm.getName(),address);
    }


}
