package mk.ukim.finki.uiktp.bookeshop.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String phoneNumber;
    private String role;
    private String password;

    public Role getRoleEnum() {
        return Role.valueOf(this.role.toUpperCase());
    }
}
