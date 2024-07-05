package gymproject.gymProject.entity.Form;

import lombok.Data;

@Data
public class MemberLoginForm {

    private String username;

    private String password;

    private boolean remember;

    public MemberLoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MemberLoginForm() {
    }
}
