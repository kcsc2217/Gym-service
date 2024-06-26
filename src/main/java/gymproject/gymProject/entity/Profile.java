package gymproject.gymProject.entity;


import gymproject.gymProject.entity.Enum.ExerciseIntensity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Profile extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "profile_id")
    private Long id;

    private int age;

    private String exercise_goal;

    @Enumerated(EnumType.STRING)
    private ExerciseIntensity exerciseIntensity;


}
