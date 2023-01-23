package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.movement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.UserBorderMovement;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.UserBorderMovementFilter;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.UserBorderMovementRepository;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;

@Repository
public class UserBorderMovementRepositoryPgSql implements UserBorderMovementRepository {

    @Autowired
    UserBorderMovementRepositoryByFilterPgSql filterPgSql;

    @Autowired
    UserBorderMovementRepositoryJpaPgSql jpaPgSql;

    @Autowired
    UserBorderMovementMapperPgSql mapperPgSql;

    @Override
    public Pagination<UserBorderMovement> get(UserBorderMovementFilter filter) {
        Pagination<UserBorderMovementPgSql> paginationData = filterPgSql.getByFilter(filter);
        return new Pagination<>(mapperPgSql.map(paginationData.getList()), paginationData.getTotal());
    }

    @Override
    public UserBorderMovement save(UserBorderMovement item) {
        UserBorderMovement result = mapperPgSql.map(jpaPgSql.save(mapperPgSql.mapToDb(item)));
        item.setId(result.getId());
        return item;
    }

    @Override
    public void delete(Long id) {
        jpaPgSql.deleteById(id);
    }

}
