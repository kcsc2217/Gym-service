package gymproject.gymProject.entity.Dto;

import gymproject.gymProject.entity.Member;
import lombok.Data;

@Data
public class MemberHomeDto {

    private Long id;

    private String name;

    private String storedFileName;

    public MemberHomeDto(Member member) {

        this.id = member.getId();
        this.name = member.getName();
        this.storedFileName = member.getProfile().getUploadFile().getStoreFileName();
    }
}
