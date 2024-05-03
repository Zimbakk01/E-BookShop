package mk.ukim.finki.uiktp.bookeshop.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Role;

@Setter
@Getter
@Data
public class UserDto {

    private String username;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String phoneNumber;
    private Role role;

    private String password;


    public UserDto() {
    }

    public UserDto(String username, String name, String surname, String email, String address, String phoneNumber, Role role,String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.password=password;
    }


}
