package gymproject.gymProject.entity.Dto.Form;

import gymproject.gymProject.entity.Enum.Role;
import gymproject.gymProject.entity.address.Address;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberForm {

    @NotEmpty(message = "아이디를 입력해주세요.")
    private String username;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Size(min = 6, message = "패스워드는 최소 6글자 입니다")
    private String password;

    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotNull(message = "휴대폰 번호를 입력해주세요.")
    private String phoneNumber;

    @NotEmpty(message = "도시를 입력해주세요.")
    private String city;

    @NotEmpty(message = "도로명을 입력해주세요.")
    private String street;
    @NotEmpty(message = "집주소를 입력해주세요.")
    private String zipcode;

    @NotNull(message = "Role is required")
    private Role role;

    public MemberForm() {
    }

    public MemberForm(String username, String password, String name, String email, String phoneNumber, String city, String street, String zipcode, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.role = role;
    }
}
