package gymproject.gymProject.controller;

import gymproject.gymProject.entity.CustomUserDetails;
import gymproject.gymProject.entity.Dto.GymContentDto;
import gymproject.gymProject.entity.Dto.GymInfDto;
import gymproject.gymProject.entity.Dto.MemberHomeDto;
import gymproject.gymProject.entity.Gym;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.exception.GymNotFoundException;
import gymproject.gymProject.entity.exception.MemberNotFoundException;
import gymproject.gymProject.repogitory.GymRepository;
import gymproject.gymProject.repogitory.MemberRepository;
import gymproject.gymProject.service.GymService;
import gymproject.gymProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GymController {

    private final GymService gymService;
    private final GymRepository gymRepository;
    private final MemberRepository memberRepository;

    @ResponseBody
    @GetMapping("/gyms")
    public Slice<GymInfDto> getGyms(@RequestParam("page") int page, @RequestParam("size") int size){
        return gymService.findAllGymInfoDto(PageRequest.of(page,size));
    }


    @GetMapping("/gyms/{id}")
    public String getGymById(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Gym gym = gymRepository.findFetchByGymId(id)
                .orElseThrow(() -> new GymNotFoundException("해당 헬스장은 존재하지 않습니다"));

        GymContentDto gymContentDto = new GymContentDto(gym);
        model.addAttribute("gym", gymContentDto);

        if (customUserDetails != null) {
            Member member = customUserDetails.getMember();
            Member findMember = memberRepository.findByIdWithProfile(member.getId())
                    .orElseThrow(() -> new MemberNotFoundException("회원이 존재하지 않습니다"));
            MemberHomeDto memberHomeDto = new MemberHomeDto(findMember);
            model.addAttribute("memberHomeDto", memberHomeDto);
        } else {
            model.addAttribute("memberHomeDto", null);
        }

        return "/gym/gym";
    }

    @GetMapping("/checkMembership")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkMembership(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Long gymId){
        log.info("쓸수 있는지 여부 컨트롤러 확인");

        Map<String, Object> response = new HashMap<>();

        if(customUserDetails != null){
            Member member = customUserDetails.getMember();

            boolean lean = gymService.existsGymAndUser(member.getId(), gymId);

            response.put("isMember", lean);

        }
        else{
            response.put("isMember", false);
            response.put("message", "로그인이 필요합니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        return ResponseEntity.ok(response);
    }










}
