package gymproject.gymProject.entity.address;


import jakarta.persistence.Embeddable;
import lombok.Data;


@Embeddable
@Data
public class Address {

    private String city;
    private String street;

    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public Address() {
    }
}
