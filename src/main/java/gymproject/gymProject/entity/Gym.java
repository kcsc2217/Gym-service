package gymproject.gymProject.entity;

import gymproject.gymProject.entity.address.Address;
import gymproject.gymProject.entity.value.StoredFile;
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

        @Column(name = "업체명")
        private String gymName;

        @Column(name = "연락처")
        private String contact_info;

        @Column(name = "주소")
        private String address;

        @OneToMany(mappedBy = "gym")
        List<Review> rivewlist = new ArrayList<>();


        @OneToMany(mappedBy = "gym")
        List<UserGym> userGymList = new ArrayList<>(); //연관관계

        @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
        List<GymImage> gymImageList = new ArrayList<>();  //여러 사진들 경로를 디비에 저장


        private String operating_our; //추후에 넣을 예정

        public Gym(String gymName, String contact_info, String address) {
            this.gymName = gymName;
            this.contact_info = contact_info;
            this.address = address;
        }

        public Gym() {

        }

        public void addGymImage(GymImage gymImage){
            gymImageList.add(gymImage);
            gymImage.setGym(this);
        }
    }
