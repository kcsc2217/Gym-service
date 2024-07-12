package gymproject.gymProject.controller;

import gymproject.gymProject.entity.Enum.ExerciseExperience;
import gymproject.gymProject.entity.Enum.ExerciseIntensity;
import gymproject.gymProject.entity.Enum.Gender;
import gymproject.gymProject.entity.Dto.Form.ProfileForm;
import gymproject.gymProject.entity.exception.MemberNotFoundException;
import gymproject.gymProject.service.MemberService;
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

    //프로필 등록 폼
    @GetMapping("/new/{id}")
    public String createForm(@PathVariable Long id,  @ModelAttribute ProfileForm profileForm, Model model){
        extracted(id, model);
        return "/profiles/new";
    }


    @PostMapping("/new/{id}")
    public String create(@PathVariable Long id, @Valid @ModelAttribute ProfileForm profileForm, BindingResult bindingResult
    ,Model model) throws IOException {

        if(bindingResult.hasErrors()){
            log.info("바인딩 에러 발생");
            extracted(id,model);
            return "/profiles/new";
        }
        log.info("바인딩 성공");

        try{
            memberService.relationShip(profileForm, id);

        }catch (MemberNotFoundException e){

            bindingResult.reject(e.getMessage());
        }

        return "redirect:/login";


    }

    private static void extracted(Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("intensity", ExerciseIntensity.values());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("exerciseExperiences", ExerciseExperience.values());
    }



}
