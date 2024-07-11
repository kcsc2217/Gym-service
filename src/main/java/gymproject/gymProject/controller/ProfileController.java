package gymproject.gymProject.controller;

import gymproject.gymProject.entity.Enum.ExerciseIntensity;
import gymproject.gymProject.entity.Form.ProfileForm;
import gymproject.gymProject.entity.exception.MemberNotFoundException;
import gymproject.gymProject.service.MemberService;
import gymproject.gymProject.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/profiles")

public class ProfileController {

    private final MemberService memberService;

    @GetMapping("/new/{id}")
    public String createForm(@PathVariable Long id,  @ModelAttribute ProfileForm profileForm, Model model){
            model.addAttribute("id", id);
            model.addAttribute("intensity", ExerciseIntensity.values());
            return "/profiles/new";
    }

    @PostMapping("/new/{id}")
    public String create(@PathVariable Long id, @Valid @ModelAttribute ProfileForm profileForm, BindingResult bindingResult) throws IOException {

        if(bindingResult.hasErrors()){
            log.info("바인딩 에러 발생");
            return "/profiles/new";
        }
        log.info("바인딩 성공");

        try{
            memberService.relationShip(profileForm, id);

        }catch (MemberNotFoundException e){

            bindingResult.reject(e.getMessage());
        }

        return "redirect:/";


    }



}
