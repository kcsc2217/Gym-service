package gymproject.gymProject.entity;


import gymproject.gymProject.entity.Enum.Gender;
import gymproject.gymProject.entity.Enum.Role;
import gymproject.gymProject.entity.address.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    @Embedded
    private Address address;


    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToOne(mappedBy = "member")
    private Profile profile;

    @OneToMany(mappedBy = "member")
    private List<Review> rivewlist = new ArrayList<>();  //내가 쓴 리뷰 보기

    public Member(String username, String password, String email, String phoneNumber, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public void assignProfile(Profile profile){
        this.profile =profile;
    }
}
