package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.user.UserPgSql;

@Repository
public interface AuthRepositoryJpaPgSql extends CrudRepository<AuthPgSql, String> {

}
