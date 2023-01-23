package ru.sfedu.ictis.mohnachev.countrymigration.domain.user;

import org.springframework.stereotype.Repository;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.UserBorderMovement;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.UserBorderMovementFilter;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;

import java.util.List;

@Repository
public interface UserRepository {

    Pagination<User> get(UserFilter filter);

    User save(User user);

}
