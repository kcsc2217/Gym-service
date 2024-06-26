package gymproject.gymProject.entity;

import gymproject.gymProject.entity.address.Address;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Gym extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "gym_id")
    private Long id;

    private String gymName;

    private String contact_info;


    @Embedded
    private Address address;

    private String operating_our;




}
