package gymproject.gymProject.controller;

import gymproject.gymProject.File.FileStore;
import gymproject.gymProject.entity.CustomUserDetails;
import gymproject.gymProject.entity.Dto.Form.ProfileUpdateForm;
import gymproject.gymProject.entity.Dto.ProfileHomeDto;
import gymproject.gymProject.entity.Enum.ExerciseExperience;
import gymproject.gymProject.entity.Enum.ExerciseIntensity;
import gymproject.gymProject.entity.Enum.Gender;
import gymproject.gymProject.entity.Dto.Form.ProfileForm;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.exception.MemberNotFoundException;
import gymproject.gymProject.repogitory.MemberRepository;
import gymproject.gymProject.service.MemberService;
import gymproject.gymProject.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/profiles")

public class ProfileController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final ProfileService profileService;

    //프로필 등록 폼
    @GetMapping("/new/{id}")
    public String createForm(@PathVariable Long id,  @ModelAttribute ProfileForm profileForm, Model model){
        extracted(id, model);
        return "/profiles/new";
    }


    // 프로필 저장
    @PostMapping("/new/{id}")
    public String create(@PathVariable Long id, @Valid @ModelAttribute ProfileForm profileForm, BindingResult bindingResult
    ,Model model) throws IOException {

        if(bindingResult.hasErrors()){
            log.info("바인딩 에러 발생");
            extracted(id,model);
            return "/profiles/new";
        }
        log.info("바인딩 성공");

        try{
            memberService.relationShip(profileForm, id);

        }catch (MemberNotFoundException e){

            bindingResult.reject(e.getMessage());
        }

        return "redirect:/login";


    }

    @GetMapping()
    public String profileForm(@AuthenticationPrincipal CustomUserDetails customUserDetails, RedirectAttributes redirectAttributes, Model model){

        if(customUserDetails == null){
            return "redirect:/login";
        }
        log.info("회원존재");
        Member member = customUserDetails.getMember();

        try{

            Member findMember = memberRepository.findByIdWithProfile(member.getId()).orElseThrow(() -> new MemberNotFoundException("해당 회원이 존재하지 않습니다"));// 멤버 뽑기
            ProfileHomeDto profileHomeDto = new ProfileHomeDto(findMember);

            log.info("homeDto 생성 완료");

            model.addAttribute("profile", profileHomeDto);

            return "/profiles/information";

        }catch (MemberNotFoundException e){
            log.info("해당 회원을 찾을 수 없습니다.");
            return "redirect:/";

        }



    }

    @GetMapping("/edit")
    public String updateForm(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        if (customUserDetails == null) {
            return "redirect:/login";
        }
        log.info("회원존재");
        Member member = customUserDetails.getMember();

        Member findMember = memberRepository.findByIdWithProfile(member.getId()).orElseThrow(() -> new MemberNotFoundException("해당 멤버가 존재하지 않습니다"));

        ProfileUpdateForm profileUpdateForm = new ProfileUpdateForm(findMember);

        log.info(profileUpdateForm.getStoredFileName());

        model.addAttribute("profileUpdateForm",profileUpdateForm);
        extracted(findMember.getId(), model);

        return "profiles/edit";
    }

    @PostMapping("/edit")
    public String update(@AuthenticationPrincipal CustomUserDetails customUserDetails, @Valid @ModelAttribute ProfileUpdateForm profileUpdateForm,
                         BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            log.info("바인딩 에러 발생");
            return "profiles/edit";
        }

        if(customUserDetails == null){
            return "redirect:/login";
        }
        log.info("데이터 바인딩 성공");

        log.info("파일: "+ String.valueOf(profileUpdateForm.getMultipartFile()));

        Member member = customUserDetails.getMember();
        profileService.updateProfile(member.getId(), profileUpdateForm);


    return "redirect:/profiles";

    }





    private static void extracted(Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("intensity", ExerciseIntensity.values());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("exerciseExperiences", ExerciseExperience.values());
    }



}
