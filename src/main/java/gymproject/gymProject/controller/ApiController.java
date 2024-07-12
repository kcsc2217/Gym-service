package gymproject.gymProject.controller;

import gymproject.gymProject.repogitory.MemberRepository;
import gymproject.gymProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.StyledEditorKit;
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
        log.info("username 중복 컨트롤러 통과");
       return memberService.usernameOverlap(username);
    }

    @GetMapping("/overlap/emailRegister")
    public Map<String, Object> emailRegister(@RequestParam String email){
        log.info("email 중복 컨트롤러 통과");
        return memberService.emailOverlap(email);
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<Boolean> emailExist(@RequestParam String email){
        log.info("컨트럴러 통과 ");
        boolean exist = memberService.existByEmail(email);

    return ResponseEntity.ok(exist);
    }



}
