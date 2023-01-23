package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.Auth;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.AuthService;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.exceptions.AuthNotFoundException;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.Authenticated;

public abstract class AuthenticatedController {

    @Autowired
    AuthService authService;

    protected Auth isAuthenticated(Authenticated request) throws AuthNotFoundException {
        if (
                (request == null) ||
                (request.getToken() == null) ||
                (request.getToken().isBlank())
        ) {
            throw new AuthNotFoundException();
        }
        return authService.getAuth(request.getToken());
    }

}
