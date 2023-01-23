package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.Auth;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.AuthService;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.exceptions.AuthNotFoundException;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.auth.requests.Authenticated;

public abstract class AuthenticatedController {

    @Autowired
    AuthService authService;

    protected Auth isAuthenticated(Authenticated request) throws AuthNotFoundException {
        return authService.getAuth(request.getToken());
    }

}
