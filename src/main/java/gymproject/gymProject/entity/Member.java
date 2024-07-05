package gymproject.gymProject.entity;


import gymproject.gymProject.entity.Enum.Gender;
import gymproject.gymProject.entity.Enum.Role;
import gymproject.gymProject.entity.address.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;


    private String username; //아이디

    private String name;

    private String password;

    private String email;

    private String phoneNumber;

    @Embedded
    private Address address;


    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Profile profile;

    @OneToMany(mappedBy = "member")
    private List<Review> rivewlist = new ArrayList<>();  //내가 쓴 리뷰 보기

    @OneToMany(mappedBy = "member")
    private List<UserGym> userGymList = new ArrayList<>();

//    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private List<Likes> likes = new ArrayList<>();

    public Member(String username, String password, String email, String phoneNumber, Role role, String name,Address address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address =address;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public void setPassword(String password){
        this.password = password;
    }


    public void assignProfile(Profile profile){
        this.profile =profile;
    }


}
