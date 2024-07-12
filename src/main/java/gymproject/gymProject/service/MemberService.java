package gymproject.gymProject.service;

import gymproject.gymProject.File.FileStore;
import gymproject.gymProject.File.UploadFile;
import gymproject.gymProject.entity.Dto.Form.MemberForm;
import gymproject.gymProject.entity.Dto.Form.MemberModifyForm;
import gymproject.gymProject.entity.Dto.Form.ProfileForm;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.Profile;
import gymproject.gymProject.entity.address.Address;
import gymproject.gymProject.entity.exception.DuplicatePasswordException;
import gymproject.gymProject.entity.exception.MemberNotFoundException;
import gymproject.gymProject.repogitory.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ProfileService profileService;
    private final FileStore fileStore;

    @Transactional
    public Long createMember(Member member){  //회원 가입

        Optional<Member> findByMember = memberRepository.findByEmailAndUsername(member.getEmail(), member.getUsername());

        validationFindMember(findByMember);

        encodePassword(member);
        //회원 가입
        memberRepository.save(member);

        return member.getId();
    }


    @Transactional //회원 수정
    public void modify(Long id, MemberModifyForm memberModifyForm){
        Member findMember = findMemberById(id);


        findMember.setMember(memberModifyForm);
    }

    // 멤버와 프로필 연관관계 맺기
    @Transactional
    public void relationShip(ProfileForm profileForm, Long memberId) throws IOException {

        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + memberId));

        UploadFile uploadFile = fileStore.storeFile(profileForm.getMultipartFile());

        //연관관계 설정
        Profile profile = profileService.alterProfile(profileForm, uploadFile);
        findMember.setProfile(profile);

        // 프로필 저장
        profileService.saveProfile(profile);



    }

    @Transactional
    public void deleteMember(Long id){
        Member member = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException("해당 멤버가 없습니다."));

        memberRepository.delete(member);
    }


    public Member findMemberById(Long id){
        Optional<Member> findMember = memberRepository.findById(id);

        return findMember.orElseThrow(()-> new MemberNotFoundException("Member not found with id: " + id));
    }


    // 중복회원 검사하기
    public HashMap<String, Object> usernameOverlap(String username){
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", memberRepository.existsByUsername(username));

        return map;
    }

    //중복 이메일 검사

    public HashMap<String, Object> emailOverlap(String email){
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", memberRepository.existsByEmail(email));

        return map;
    }

    public boolean existByEmail(String email){
      return memberRepository.existsByEmail(email);
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
