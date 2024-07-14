package gymproject.gymProject.entity.Dto.Form;

import gymproject.gymProject.custom.ValidMultipartFile;
import gymproject.gymProject.entity.Enum.ExerciseExperience;
import gymproject.gymProject.entity.Enum.ExerciseIntensity;
import gymproject.gymProject.entity.Enum.Gender;
import gymproject.gymProject.entity.Member;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileUpdateForm {

    @NotEmpty(message = "이름은 필수입니다")
    private String name;

    @NotNull(message = "성별은 필수입니다.")
    private Gender gender;

    @NotNull(message = "나이는 필수입니다.")
    private int age;

    @NotNull(message = "운동경험은 필수입니다.")
    private ExerciseExperience exerciseExperience;

    @NotNull(message = "운동 강도체크는 필수입니다.")
    private ExerciseIntensity exerciseIntensity;

    @NotEmpty(message = "운동 목표를 입력해주세요")
    private String exercise_goal;

    @NotEmpty(message = "몸무게를 입력하세요")
    private String weight;

    @NotEmpty(message = "키를 입력하세요")
    private String height;

    private String storedFileName; //저장된 사진을 보여줄 변수

    private String uploadFileName; // 수정할 업로드 파일 네임

    @ValidMultipartFile   //커스텀한 valid
    private MultipartFile multipartFile; // 바꿀사진을 보여줄 변수



    public ProfileUpdateForm(Member member) {
        this.name = member.getName();
        this.gender = member.getProfile().getGender();
        this.age = member.getProfile().getAge();
        this.exerciseExperience = member.getProfile().getExerciseExperience();
        this.exerciseIntensity = member.getProfile().getExerciseIntensity();
        this.exercise_goal = member.getProfile().getExercise_goal();
        this.weight = member.getProfile().getWeight();
        this.height = member.getProfile().getHeight();
        this.storedFileName = member.getProfile().getUploadFile().getStoreFileName();
    }

    public ProfileUpdateForm() {
    }
}
