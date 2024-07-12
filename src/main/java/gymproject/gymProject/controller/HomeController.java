package gymproject.gymProject.controller;

import gymproject.gymProject.File.FileStore;
import gymproject.gymProject.entity.CustomUserDetails;
import gymproject.gymProject.entity.Dto.MemberHomeDto;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.exception.MemberNotFoundException;
import gymproject.gymProject.repogitory.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final FileStore fileStore;
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model){


        if(customUserDetails == null){
            model.addAttribute("memberHomeDto", null);
        }
        else{
            Member member = customUserDetails.getMember();
            Member findMember = memberRepository.findByIdWithProfile(member.getId()).orElseThrow(() -> new MemberNotFoundException("회원이 존재하지 않습니다"));

            MemberHomeDto memberHomeDto = new MemberHomeDto(findMember);

            model.addAttribute("memberHomeDto", memberHomeDto);

        }



        return "index";
    }

    @ResponseBody
    @GetMapping("/image/{filename}")
    public Resource loadImage(@PathVariable String filename) throws MalformedURLException {
        String fullPath = fileStore.getFullPath(filename);
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

}
