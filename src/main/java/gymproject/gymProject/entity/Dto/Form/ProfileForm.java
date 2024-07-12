package gymproject.gymProject.entity.Dto.Form;


import gymproject.gymProject.entity.Enum.ExerciseExperience;
import gymproject.gymProject.entity.Enum.ExerciseIntensity;
import gymproject.gymProject.entity.Enum.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileForm {

    @NotNull(message = "나이는 필수입니다.")
    private int age; //나이

    @NotEmpty(message = "나이를 입력하세요")
    private String exercise_goal; //운동 목표

    @NotNull(message = "운동 강도체크는 필수입니다.")
    private ExerciseIntensity exerciseIntensity; // 원하는 강도

    @NotNull(message = "운동경험은 필수입니다.")
    private ExerciseExperience exerciseExperience;

    @NotEmpty(message = "몸무게를 입력하세요")
    private String weight; // 몸무게

    @NotEmpty(message = "키를 입력하세요")
    private String height; // 키

    @NotNull(message = "성별은 필수입니다.")
    private Gender gender;

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
