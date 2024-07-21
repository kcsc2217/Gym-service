package gymproject.gymProject.entity.Dto;

import gymproject.gymProject.entity.Gym;
import gymproject.gymProject.entity.GymImage;
import gymproject.gymProject.entity.Review;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class GymContentDto {

    private String gymName;

    private String contact_info;

    private String address;

    private List<String> storedFiles;

    public GymContentDto(Gym gym) {
        this.gymName = gym.getGymName();
        this.contact_info = gym.getContact_info();
        this.address = gym.getAddress();
        this.storedFiles = gym.getGymImageList().stream().map(GymImage::getImagePath).toList();

    }

    public GymContentDto() {
    }
}
