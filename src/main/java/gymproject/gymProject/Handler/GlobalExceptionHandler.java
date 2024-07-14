package gymproject.gymProject.Handler;

import gymproject.gymProject.entity.exception.MemberNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public String handleMemberNotFoundException(MemberNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/memberNotFound";
    }
}
