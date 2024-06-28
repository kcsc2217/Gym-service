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

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    public Profile(int age, String exercise_goal, ExerciseIntensity exerciseIntensity) {
        this.age = age;
        this.exercise_goal = exercise_goal;
        this.exerciseIntensity = exerciseIntensity;

    }

    public void setMember(Member newMember){
        this.member = newMember;
        newMember.setProfile(this);
    }



}
