package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.movement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBorderMovementRepositoryJpaPgSql extends CrudRepository<UserBorderMovementPgSql, Long> {

}
