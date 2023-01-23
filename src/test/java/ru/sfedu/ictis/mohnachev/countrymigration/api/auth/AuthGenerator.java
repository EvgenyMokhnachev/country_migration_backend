package ru.sfedu.ictis.mohnachev.countrymigration.api.auth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.Auth;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.AuthService;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.exceptions.PasswordIsInvalidException;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.exceptions.PasswordIsWrongException;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.AuthRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses.AuthResponse;

import java.util.UUID;

@Service
public class AuthGenerator {

    @Autowired
    AuthService authService;

    public Auth generateAuth() {
        String email = UUID.randomUUID().toString() + "@sfedu.ru";
        String pass = UUID.randomUUID().toString();

        try {
            return authService.auth(email, pass);
        } catch (PasswordIsInvalidException | PasswordIsWrongException e) {
            throw new RuntimeException(e);
        }
    }

}
