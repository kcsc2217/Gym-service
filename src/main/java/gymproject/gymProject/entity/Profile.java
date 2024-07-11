package gymproject.gymProject.entity;


import gymproject.gymProject.File.UploadFile;
import gymproject.gymProject.entity.Enum.ExerciseIntensity;
import gymproject.gymProject.entity.Form.ProfileForm;
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

    private String exercise_goal;

    @Enumerated(EnumType.STRING)
    private ExerciseIntensity exerciseIntensity;

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
        this.exercise_goal = profileForm.getExercise_goal();
        this.exerciseIntensity = profileForm.getExerciseIntensity();
        this.uploadFile = uploadFile;
    }


    public  void setMember(Member member){
        this.member = member;
    }





}
