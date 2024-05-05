package mk.ukim.finki.uiktp.bookeshop.auth;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.uiktp.bookeshop.config.JwtService;
import mk.ukim.finki.uiktp.bookeshop.model.User;
import mk.ukim.finki.uiktp.bookeshop.model.enumeration.Role;
import mk.ukim.finki.uiktp.bookeshop.repository.UserRepository;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        String encoded=passwordEncoder.encode(request.getPassword());
        var user = User.builder()
                .username(request.getUsername())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .password(encoded)
                .role(request.getRoleEnum())
                .isEnabled(true)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        try {
            authenticationManager.authenticate(token);
        } catch (Exception e) {
            System.out.println(request.getUsername() + " " + request.getPassword());  // Separate username and password for better readability
            // Check if the exception type is related to account locking (e.g., AccountStatusException)
            if (e instanceof AccountStatusException) {
                System.out.println("Account locked! Reason: " + e.getMessage());
            } else {
                System.out.println("Authentication failed: " + e.getMessage());
            }
            return null;
        }
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        System.out.println("Retrieved user: " + user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}