package gymproject.gymProject.entity;

import gymproject.gymProject.entity.address.Address;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Gym extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "gym_id")
    private Long id;

    private String gymName;

    private String contact_info;

    @OneToMany(mappedBy = "gym")
    List<Review> rivewlist = new ArrayList<>();

    @OneToMany(mappedBy = "gym")
    List<UserGym> userGymList = new ArrayList<>();



    @Embedded
    private Address address;

    private String operating_our;






}
