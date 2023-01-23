package ru.sfedu.ictis.mohnachev.countrymigration.domain.movement;

import org.springframework.stereotype.Repository;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;

import java.util.List;

@Repository
public interface UserBorderMovementRepository {

    Pagination<UserBorderMovement> get(UserBorderMovementFilter filter);

    UserBorderMovement save(UserBorderMovement item);

}
