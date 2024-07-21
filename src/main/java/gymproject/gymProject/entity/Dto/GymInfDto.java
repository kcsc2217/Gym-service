package gymproject.gymProject.entity.Dto;

import gymproject.gymProject.entity.Gym;
import gymproject.gymProject.entity.GymImage;
import lombok.Data;

@Data
public class GymInfDto {

    private Long id;

    private String gymName;

    private String address;

    private String contact;

    private String storePath;

    public GymInfDto(Gym gym) {
        this.id = gym.getId();
        this.gymName = gym.getGymName();
        this.address = gym.getAddress();
        this.contact = gym.getContact_info();
        this.storePath = gym.getGymImageList().get(0).getImagePath();
    }

    public GymInfDto() {
    }
}
