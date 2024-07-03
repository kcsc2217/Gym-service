package gymproject.gymProject.controller;

import gymproject.gymProject.repogitory.MemberRepository;
import gymproject.gymProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ApiController {
    private final MemberService memberService;
    @GetMapping("/overlap/usernameRegister")
    public Map<String, Object> usernameRegister(@RequestParam String username){
        log.info("컨트롤러 통과");
       return memberService.usernameOverlap(username);
    }

}
