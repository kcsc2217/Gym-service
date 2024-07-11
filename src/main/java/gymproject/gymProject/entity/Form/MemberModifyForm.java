package gymproject.gymProject.entity.Form;

import gymproject.gymProject.entity.Member;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberModifyForm
{


    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    @NotNull(message = "phoneNumber is required")
    private String phoneNumber;

    @NotEmpty(message = "City is required")
    private String city;

    @NotEmpty(message = "Street is required")
    private String street;
    @NotEmpty(message = "Zipcode is required")
    private String zipcode;

    public MemberModifyForm(String name, String phoneNumber, String city, String street, String zipcode) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public MemberModifyForm(Member member){
        this.name = member.getName();
        this.phoneNumber = member.getPhoneNumber();
        this.city = member.getAddress().getCity();
        this.street = member.getAddress().getStreet();
        this.zipcode = member.getAddress().getZipcode();
    }

    public MemberModifyForm() {
    }
}
