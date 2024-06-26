package gymproject.gymProject.entity;

import gymproject.gymProject.entity.Enum.MemberShip;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter

public class UserGym extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "user_gym_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @Enumerated(EnumType.STRING)
    private MemberShip memberShip;



}
