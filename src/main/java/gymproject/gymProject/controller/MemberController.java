package gymproject.gymProject.controller;

import gymproject.gymProject.entity.CustomUserDetails;
import gymproject.gymProject.entity.Dto.Form.MemberDeleteForm;
import gymproject.gymProject.entity.Enum.Role;
import gymproject.gymProject.entity.Dto.Form.MemberForm;
import gymproject.gymProject.entity.Dto.Form.MemberLoginForm;
import gymproject.gymProject.entity.Dto.Form.MemberModifyForm;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.exception.DuplicatePasswordException;
import gymproject.gymProject.entity.exception.MemberNotFoundException;
import gymproject.gymProject.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String createdMember(@Valid @ModelAttribute MemberForm memberForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

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

         redirectAttributes.addAttribute("memberId", member.getId());

        return "redirect:/profiles/new/{memberId}";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginForm", new MemberLoginForm());

        return "/members/login";
    }

    @GetMapping("/update")
    public String updateForm(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model){

        if(customUserDetails == null){
            return "redirect:/login";
        }else{
            Member findMember = customUserDetails.getMember();
            Member member = memberService.findMemberById(findMember.getId());

            model.addAttribute("memberModifyForm", new MemberModifyForm(member));

        }

        return "members/update";

    }

    @PostMapping("/update")
    public String update( @Valid @ModelAttribute MemberModifyForm memberModifyForm,
                          BindingResult bindingResult, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        if (bindingResult.hasErrors()) {
            log.info("필드 에러 발생");
            return "members/update";
        }
            if(customUserDetails == null){
            return "redirect:/login";
        }
            log.info("회원 존재");
            Member member = customUserDetails.getMember();

            try{
                memberService.modify(member.getId(), memberModifyForm);
                return "redirect:/";
            }catch (MemberNotFoundException e){
                bindingResult.reject("errors", e.getMessage());
                return "members/update";
            }




    }

    @GetMapping("/delete")
    public String deleteForm(@ModelAttribute MemberDeleteForm memberDeleteForm){
        return "members/delete";
    }

    @PostMapping("/delete")
    public String delete(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                         @Valid @ModelAttribute MemberDeleteForm memberDeleteForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("필드 에러 발생");
            return "members/delete";
        }

        if (customUserDetails == null) {
            return "redirect:/login";
        }

        log.info("회원 존재");
        Member member = customUserDetails.getMember();

        try {
            memberService.deleteMember(member.getId());
            return "redirect:/login"; // 성공 메시지를 표시하는 페이지로 리디렉션
        } catch (MemberNotFoundException e) {
            bindingResult.reject("errors", e.getMessage());
            return "members/delete";
        }
    }






}
