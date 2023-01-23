package ru.sfedu.ictis.mohnachev.countrymigration.domain.auth;

import org.springframework.stereotype.Repository;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;

import java.util.List;

@Repository
public interface AuthRepository {

    Pagination<Auth> get(AuthFilter filter);

    Auth save(Auth auth);

}
