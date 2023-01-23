package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.AuthService;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.exceptions.PasswordIsInvalidException;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.exceptions.PasswordIsWrongException;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.AuthRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses.AuthResponse;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping( "/auth")
    private @ResponseBody AuthResponse auth(
            @RequestBody AuthRequest request
    ) throws
            PasswordIsInvalidException,
            PasswordIsWrongException
    {
        return new AuthResponse(
                authService.auth(
                        request.getEmail(),
                        request.getPass()
                )
        );
    }

}
