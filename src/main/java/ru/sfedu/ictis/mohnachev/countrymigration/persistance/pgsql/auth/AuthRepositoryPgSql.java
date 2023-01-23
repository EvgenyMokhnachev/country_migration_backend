package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.Auth;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.AuthFilter;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.AuthRepository;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;

import java.util.List;

@Repository
public class AuthRepositoryPgSql implements AuthRepository {

    @Autowired
    AuthRepositoryByFilterPgSql filterPgSql;

    @Autowired
    AuthRepositoryJpaPgSql jpaPgSql;

    @Autowired
    AuthMapperPgSql mapperPgSql;

    @Override
    public Pagination<Auth> get(AuthFilter filter) {
        Pagination<AuthPgSql> paginationData = filterPgSql.getByFilter(filter);
        return new Pagination<>(mapperPgSql.map(paginationData.getList()), paginationData.getTotal());
    }

    @Override
    public Auth save(Auth item) {
        return mapperPgSql.map(jpaPgSql.save(mapperPgSql.mapToDb(item)));
    }

}
