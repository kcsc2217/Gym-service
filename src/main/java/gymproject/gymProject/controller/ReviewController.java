package gymproject.gymProject.controller;

import gymproject.gymProject.entity.Dto.Form.ReviewForm;
import gymproject.gymProject.entity.Review;
import gymproject.gymProject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/review")

public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/crate")
    public String crateForm(@ModelAttribute ReviewForm reviewForm) {
        return "review/crate";
    }





}
