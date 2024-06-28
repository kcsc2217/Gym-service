package gymproject.gymProject.entity;


import gymproject.gymProject.entity.Enum.ExerciseIntensity;
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

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    public Profile(int age, String exercise_goal, ExerciseIntensity exerciseIntensity, Member member) {
        this.age = age;
        this.exercise_goal = exercise_goal;
        this.exerciseIntensity = exerciseIntensity;
        setMember(member);
    }

    public void setMember(Member newMember){
        this.member = newMember;
        newMember.assignProfile(this);
    }



}
