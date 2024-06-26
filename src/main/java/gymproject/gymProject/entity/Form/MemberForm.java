package gymproject.gymProject.entity.Form;

import gymproject.gymProject.entity.Enum.Role;
import gymproject.gymProject.entity.address.Address;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberForm {

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;

    @NotEmpty(message = "Phone number is required")
    private String email;

    @NotNull(message = "Address is required")
    private String phoneNumber;

    @NotEmpty(message = "City is required")
    private String city;

    @NotEmpty(message = "Street is required")
    private String street;
    @NotEmpty(message = "Zipcode is required")
    private String zipcode;

    @NotNull(message = "Role is required")
    private Role role;

    public MemberForm() {
    }

    public MemberForm(String username, String password, String email, String phoneNumber, String city, String street, String zipcode, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.role = role;
    }
}
