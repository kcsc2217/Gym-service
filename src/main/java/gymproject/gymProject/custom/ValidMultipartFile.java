package gymproject.gymProject.custom;

import jakarta.validation.Payload;

public @interface ValidMultipartFile {

    String message() default "Invalid file";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
