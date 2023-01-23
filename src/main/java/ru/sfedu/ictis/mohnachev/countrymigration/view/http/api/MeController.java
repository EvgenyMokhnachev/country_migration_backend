package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.Auth;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.exceptions.AuthNotFoundException;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.user.UserFilter;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.user.UserRepository;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.MeRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses.UserResponse;

@RestController
public class MeController extends AuthenticatedController {

    @Autowired
    UserRepository userRepository;

    @PostMapping( "/user/me")
    private @ResponseBody UserResponse auth(
            @RequestBody MeRequest request
    ) throws
            AuthNotFoundException
    {
        Auth auth = this.isAuthenticated(request);
        return new UserResponse(
                userRepository.get(UserFilter.create()
                        .byId(auth.getUserId())
                ).getFirst()
        );
    }

}
