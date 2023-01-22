package ru.sfedu.ictis.mohnachev.countrymigration.domain.movement;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBorderMovementRepository {

    List<UserBorderMovement> get(UserBorderMovementFilter filter);

}
