package gymproject.gymProject.entity.Dto;

import gymproject.gymProject.entity.Member;
import lombok.Data;

@Data
public class MemberHomeDto {

    private String name;

    public MemberHomeDto(Member member) {
        this.name = member.getName();
    }
}
