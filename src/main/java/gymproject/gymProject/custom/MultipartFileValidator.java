package gymproject.gymProject.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileValidator implements ConstraintValidator<ValidMultipartFile, MultipartFile> {
    @Override
    public void initialize(ValidMultipartFile constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if(file.isEmpty() || file ==null){
            return false;
        }

        return true;
    }
}
