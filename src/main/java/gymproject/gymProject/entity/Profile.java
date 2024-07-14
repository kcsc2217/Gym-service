package gymproject.gymProject.entity;


import gymproject.gymProject.File.UploadFile;
import gymproject.gymProject.entity.Dto.Form.ProfileUpdateForm;
import gymproject.gymProject.entity.Enum.ExerciseExperience;
import gymproject.gymProject.entity.Enum.ExerciseIntensity;
import gymproject.gymProject.entity.Enum.Gender;
import gymproject.gymProject.entity.Dto.Form.ProfileForm;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "profile_id")
    private Long id;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    // 운동목표
    private String exercise_goal;

    // 운동 경험
    @Enumerated(EnumType.STRING)
    private ExerciseExperience exerciseExperience;

    // 운동 강도
    @Enumerated(EnumType.STRING)
    private ExerciseIntensity exerciseIntensity;

    private String weight; // 몸무게

    private String height; // 키



    private UploadFile uploadFile; // 프로필 저장 시 파일경로 저장

    @OneToOne(mappedBy = "profile")
    private Member member;

    public Profile(int age, String exercise_goal, ExerciseIntensity exerciseIntensity) {
        this.age = age;
        this.exercise_goal = exercise_goal;
        this.exerciseIntensity = exerciseIntensity;
    }

    public Profile(ProfileForm profileForm, UploadFile uploadFile){
        this.age = profileForm.getAge();
        this.exerciseExperience = profileForm.getExerciseExperience();
        this.gender = profileForm.getGender();
        this.weight = profileForm.getWeight();
        this.height = profileForm.getHeight();
        this.exercise_goal = profileForm.getExercise_goal();
        this.exerciseIntensity = profileForm.getExerciseIntensity();
        this.uploadFile = uploadFile;
    }

    public void updateContent(ProfileUpdateForm profileUpdateForm){
        this.gender = profileUpdateForm.getGender();
        this.age = profileUpdateForm.getAge();
        this.exerciseExperience = profileUpdateForm.getExerciseExperience();
        this.exerciseIntensity = profileUpdateForm.getExerciseIntensity();
        this.weight = profileUpdateForm.getWeight();
        this.height = profileUpdateForm.getHeight();
        this.exercise_goal = profileUpdateForm.getExercise_goal();
        this.uploadFile.setStoreFileName(profileUpdateForm.getStoredFileName());
        this.uploadFile.setUploadFileName(profileUpdateForm.getUploadFileName());
    }

    public  void setMember(Member member){
        this.member = member;
    }

    public void setGender(Gender gender){
        this.gender = gender;
    }





}
