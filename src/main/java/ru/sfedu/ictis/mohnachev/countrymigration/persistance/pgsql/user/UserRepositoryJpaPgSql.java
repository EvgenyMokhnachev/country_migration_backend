package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.user.User;

@Repository
public interface UserRepositoryJpaPgSql extends CrudRepository<UserPgSql, Long> {

}
