package gymproject.gymProject.entity.Form;


import gymproject.gymProject.entity.Enum.ExerciseIntensity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileForm {

    private int age; //나이

    private String exercise_goal; //운동 목표

    private ExerciseIntensity exerciseIntensity; // 원하는 강도

    private MultipartFile multipartFile; // 프로필 사진

    public ProfileForm(int age, String exercise_goal, ExerciseIntensity exerciseIntensity, MultipartFile multipartFile) {
        this.age = age;
        this.exercise_goal = exercise_goal;
        this.exerciseIntensity = exerciseIntensity;
        this.multipartFile = multipartFile;
    }

    public ProfileForm(int age, String exercise_goal, ExerciseIntensity exerciseIntensity) {
        this.age = age;
        this.exercise_goal = exercise_goal;
        this.exerciseIntensity = exerciseIntensity;
    }

    public ProfileForm() {
    }

}
