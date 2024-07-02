package gymproject.gymProject.controller;

import gymproject.gymProject.entity.Enum.Role;
import gymproject.gymProject.entity.Form.MemberForm;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class MemberController {
    private final MemberService memberService;


    @GetMapping("/signup")
    public String createMemberForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        model.addAttribute("roles", Role.values()); // Role Enum 값을 모델에 추가

        return "/members/signup";
    }

    @PostMapping("/signup")
    public String createdMember(@Valid @ModelAttribute MemberForm memberForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "/members/signup";
        }
        Member member = memberService.alertMember(memberForm);

        memberService.createMember(member);

        return "redirect:/login";
    }


}
