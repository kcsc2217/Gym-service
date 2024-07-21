package gymproject.gymProject.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class GymImage {

    @Id
    @GeneratedValue
    @Column(name = "gymImage_id")
    private Long id;

    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    public GymImage(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setGym(Gym gym){
        this.gym = gym;
    }


}
