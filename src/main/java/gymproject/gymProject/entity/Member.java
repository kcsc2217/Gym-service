package gymproject.gymProject.entity;


import gymproject.gymProject.entity.Dto.Form.ProfileUpdateForm;
import gymproject.gymProject.entity.Enum.Gender;
import gymproject.gymProject.entity.Enum.Role;
import gymproject.gymProject.entity.Dto.Form.MemberModifyForm;
import gymproject.gymProject.entity.address.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "member")
    private List<Review> rivewlist = new ArrayList<>();  //내가 쓴 리뷰 보기

    @OneToMany(mappedBy = "member")
    private List<UserGym> userGymList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();

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


    public void setMember(MemberModifyForm memberModifyForm){
        Address newAddress = new Address(memberModifyForm.getCity(), memberModifyForm.getStreet(), memberModifyForm.getZipcode());

        this.name = memberModifyForm.getName();
        this.phoneNumber = memberModifyForm.getPhoneNumber();
        this.address = newAddress;

    }

    public void setProfile(Profile profile){
        this.profile =profile;
        profile.setMember(this);
    }



    public void updateProfile(ProfileUpdateForm profileUpdateForm){
        this.name = profileUpdateForm.getName();
        this.profile.updateContent(profileUpdateForm);
    }


    public void assignProfile(Profile profile){
        this.profile =profile;
    }


}
