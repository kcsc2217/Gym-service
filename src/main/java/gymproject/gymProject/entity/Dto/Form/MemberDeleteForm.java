package gymproject.gymProject.entity.Dto.Form;

import lombok.Data;

@Data
public class MemberDeleteForm {

    private String email;

    public MemberDeleteForm(String email) {
        this.email = email;
    }

    public MemberDeleteForm() {
    }
}
