package gymproject.gymProject.entity.Form;

import lombok.Data;

@Data
public class MemberLoginForm {

    private String username;

    private String password;

    public MemberLoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MemberLoginForm() {
    }
}
