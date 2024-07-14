package gymproject.gymProject.entity.Dto;

import gymproject.gymProject.entity.Enum.ExerciseExperience;
import gymproject.gymProject.entity.Enum.ExerciseIntensity;
import gymproject.gymProject.entity.Enum.Gender;
import gymproject.gymProject.entity.Member;
import lombok.Data;

@Data
public class ProfileHomeDto {

    private String name;

    private String gender;

    private int age;


    private String exerciseExperience;

    private String exerciseIntensity;

    private String exercise_goal;


    private String weight;

    private String height;

    private String storedFileName;

    public ProfileHomeDto(Member member) {
        this.name = member.getName();
        this.age = member.getProfile().getAge();
        this.exercise_goal = member.getProfile().getExercise_goal();
        this.gender = member.getProfile().getGender().getDescription();
        this.exerciseExperience = member.getProfile().getExerciseExperience().getDescription();
        this.exerciseIntensity = member.getProfile().getExerciseIntensity().getDescription();
        this.weight = member.getProfile().getWeight();
        this.height = member.getProfile().getHeight();
        this.storedFileName = member.getProfile().getUploadFile().getStoreFileName();
    }

    public ProfileHomeDto() {
    }
}
