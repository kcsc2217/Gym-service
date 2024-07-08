package gymproject.gymProject.controller;

import gymproject.gymProject.service.MailService;
import gymproject.gymProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final MailService mailService;
    private int number;

    @PostMapping("/mailSend")
    public HashMap<String, Object> mailSend(@RequestParam  String mail){
        HashMap<String, Object> map = new HashMap<>();

        log.info("메일 전송 컨트롤러 통과");


        try{
            number = mailService.sendMail(mail);
            String num = String.valueOf(number);

            map.put("success", Boolean.TRUE);
            map.put("number", num);

        }catch (Exception e){
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }

    @PostMapping("/verifyCode")
    public HashMap<String, Object> verifyCode(@RequestParam int code){
        HashMap<String, Object> map = new HashMap<>();

        if(code == number){
            map.put("success", Boolean.TRUE);
        }else{
            map.put("success", Boolean.FALSE);
            map.put("error", "유효하지 않는 코드입니다.");
        }

        return map;
    }

}
