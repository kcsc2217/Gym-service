package gymproject.gymProject.Handler;

import gymproject.gymProject.entity.exception.GymNotFoundException;
import gymproject.gymProject.entity.exception.MemberNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public String handleMemberNotFoundException(MemberNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/memberNotFound";
    }

    @ExceptionHandler(GymNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleGymNotFoundException(GymNotFoundException ex, Model model) {
        log.error(ex.getMessage());
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/404"; // 404 에러 페이지로 이동
    }
}
