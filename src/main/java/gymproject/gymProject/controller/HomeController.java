package gymproject.gymProject.controller;

import gymproject.gymProject.File.FileStore;
import gymproject.gymProject.entity.CustomUserDetails;
import gymproject.gymProject.entity.Dto.GymInfDto;
import gymproject.gymProject.entity.Dto.MemberHomeDto;
import gymproject.gymProject.entity.Gym;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.exception.MemberNotFoundException;
import gymproject.gymProject.repogitory.GymRepository;
import gymproject.gymProject.repogitory.MemberRepository;
import gymproject.gymProject.service.GymService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final FileStore fileStore;
    private final MemberRepository memberRepository;
    private final GymService gymService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model, RedirectAttributes redirectAttributes){

        Slice<GymInfDto> allGymInfoDto = gymService.findAllGymInfoDto(PageRequest.of(0,10));


        model.addAttribute("gyms" , allGymInfoDto.getContent());
        model.addAttribute("hasNext", allGymInfoDto.hasNext());

        if(customUserDetails == null){
            model.addAttribute("memberHomeDto", null);
        }
        else{
            Member member = customUserDetails.getMember();

            try{
                Member findMember = memberRepository.findByIdWithProfile(member.getId()).orElseThrow(() -> new MemberNotFoundException("회원이 존재하지 않습니다"));
                MemberHomeDto memberHomeDto = new MemberHomeDto(findMember);

                model.addAttribute("memberHomeDto", memberHomeDto);
            }catch (MemberNotFoundException e){
                redirectAttributes.addFlashAttribute("globalErrors", e.getMessage());
                return "redirect:/login";
            }

        }
        return "index";
    }

    @ResponseBody
    @GetMapping("/image/{filename}")
    public Resource loadImage(@PathVariable String filename) throws MalformedURLException {
        String fullPath = fileStore.getFullPath(filename);
        log.info("fullPath = "  + fullPath);
        log.info("파일 컨트롤러 통과");
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    // 페이지 더 보여주기 위한 api

}
