package gymproject.gymProject.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter

public class GymImage {

    @Id
    @GeneratedValue
    private Long id;

    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;



}
