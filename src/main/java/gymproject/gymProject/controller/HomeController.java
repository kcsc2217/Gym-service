package gymproject.gymProject.controller;

import gymproject.gymProject.entity.CustomUserDetails;
import gymproject.gymProject.entity.Dto.MemberHomeDto;
import gymproject.gymProject.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor

public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model){


        if(customUserDetails == null){
            model.addAttribute("memberHomeDto", null);
        }
        else{
            Member member = customUserDetails.getMember();

            MemberHomeDto memberHomeDto = new MemberHomeDto(member);

            model.addAttribute("memberHomeDto", memberHomeDto);
        }



        return "index";
    }
}
