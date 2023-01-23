package ru.sfedu.ictis.mohnachev.countrymigration.domain.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.exceptions.AuthNotFoundException;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.exceptions.PasswordIsInvalidException;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.exceptions.PasswordIsWrongException;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.user.User;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.user.UserFilter;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.user.UserRepository;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;

import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PassHashUtil passHashUtil;

    public Auth auth(String email, String pass) throws
            PasswordIsInvalidException,
            PasswordIsWrongException
    {
        User user = null;

        Pagination<User> users = userRepository.get(UserFilter.create().byEmail(email));
        if (users == null || users.isEmpty()) {
            user = createUser(email, pass);
        } else {
            user = users.getFirst();
        }

        if (!user.getPass().equals(passHashUtil.createMD5Hash(pass))) {
            throw new PasswordIsWrongException();
        }

        Auth auth = new Auth();
        auth.setToken(UUID.randomUUID().toString());
        auth.setUserId(user.getId());
        return authRepository.save(auth);
    }

    public Auth getAuth(String token) throws
            AuthNotFoundException
    {
        Pagination<Auth> auths = authRepository.get(AuthFilter.create().byToken(token));
        if (auths == null || auths.isEmpty()) {
            throw new AuthNotFoundException();
        }
        return auths.getFirst();
    }

    private User createUser(String email, String pass) throws
            PasswordIsInvalidException
    {
        if (pass == null || pass.isBlank()) {
            throw new PasswordIsInvalidException();
        }

        User user = new User();
        user.setEmail(email);
        user.setPass(passHashUtil.createMD5Hash(pass));
        return userRepository.save(user);
    }

}
