package gymproject.gymProject.controller;

import gymproject.gymProject.entity.Enum.Role;
import gymproject.gymProject.entity.Form.MemberForm;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.exception.DuplicatePasswordException;
import gymproject.gymProject.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j

public class MemberController {
    private final MemberService memberService;


    @GetMapping("/signup")
    public String createMemberForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        model.addAttribute("roles", Role.values()); // Role Enum 값을 모델에 추가

        return "/members/signup";
    }

    @PostMapping("/signup")
    public String createdMember(@Valid @ModelAttribute MemberForm memberForm, BindingResult bindingResult, Model model){

        model.addAttribute("roles", Role.values());

        if(bindingResult.hasErrors()){
            log.info("바인딩 에러 발생");
            return "/members/signup";
        }
        log.info("바인딩 성공");
        Member member = memberService.alertMember(memberForm);


         try{
             memberService.createMember(member);
         }catch ( DuplicatePasswordException e){
             bindingResult.reject("errors", e.getMessage());
             log.info("바인딩 에러 발생");
             return "/members/signup";

         }

        log.info("회원 가입 완료");

        return "redirect:/login";
    }


}
