package mk.ukim.finki.uiktp.bookeshop.model.exceptions;

import org.springframework.security.core.AuthenticationException;

public class PasswordDoNotMatch extends AuthenticationException {
    public PasswordDoNotMatch(){
        super("Password do not match!");
    }
}