package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.user.User;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.user.UserFilter;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.user.UserRepository;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;

@Repository
public class UserRepositoryPgSql implements UserRepository {

    @Autowired
    UserRepositoryByFilterPgSql filterPgSql;

    @Autowired
    UserRepositoryJpaPgSql jpaPgSql;

    @Autowired
    UserMapperPgSql mapperPgSql;

    @Override
    public Pagination<User> get(UserFilter filter) {
        Pagination<UserPgSql> paginationData = filterPgSql.getByFilter(filter);
        return new Pagination<>(mapperPgSql.map(paginationData.getList()), paginationData.getTotal());
    }

    @Override
    public User save(User item) {
        User result = mapperPgSql.map(jpaPgSql.save(mapperPgSql.mapToDb(item)));
        item.setId(result.getId());
        return item;
    }

}
