package ru.sfedu.ictis.mohnachev.countrymigration.domain.movement;

import org.springframework.stereotype.Repository;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;

public interface UserBorderMovementRepository {

    Pagination<UserBorderMovement> get(UserBorderMovementFilter filter);

    UserBorderMovement save(UserBorderMovement item);

    void delete(Long id);

}
