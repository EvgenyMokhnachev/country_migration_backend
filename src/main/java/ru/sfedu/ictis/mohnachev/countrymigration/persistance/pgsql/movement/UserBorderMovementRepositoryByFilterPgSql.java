package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.movement;

import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.UserBorderMovementFilter;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.BasePgSqlRepository;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.BaseQuery;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.OrderDirection;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;

import java.util.List;

@Repository
public class UserBorderMovementRepositoryByFilterPgSql extends BasePgSqlRepository<UserBorderMovementPgSql, UserBorderMovementFilter> {

    private static final String FROM = "cmp.movements";
    private static final String AS = "ubm";

    @Override
    public Pagination<UserBorderMovementPgSql> getByFilter(UserBorderMovementFilter filter) {
        if (filter.getLimit() == null && filter.getIds() != null && !filter.getIds().isEmpty()) {
            filter.setLimit(filter.getIds().size());
        }

        BaseQuery baseQuery = new BaseQuery(
                FROM, AS,
                String.format("%s.*", AS),
                String.format("%s.id", AS),
                filter.getCalculateTotal(),
                filter.getLimit(),
                filter.getOffset()
        );
        baseQuery.setOptimizedSelectForTotalQuery(String.format("%s.id", AS));

        filterByIds(filter.getIds(), baseQuery);
        filterByUserIds(filter.getUserIds(), baseQuery);

        injectOrders(filter, baseQuery);

        String sql = buildNativeSqlQuery(baseQuery);
        Query query = entityManager.createNativeQuery(sql, UserBorderMovementPgSql.class);
        injectParameters(query, baseQuery.getParameters());
        List<UserBorderMovementPgSql> items = query.getResultList();

        return new Pagination<>(items, getTotal(baseQuery));
    }

    private void filterByIds(List<Long> ids, BaseQuery query) {
        if (ids == null || ids.isEmpty()) return;
        query.addParameter("filterIds", ids);
        query.addWheres(String.format(" AND (%1$s.id IN :filterIds)", AS));
    }

    private void filterByUserIds(List<Long> ids, BaseQuery query) {
        if (ids == null || ids.isEmpty()) return;
        query.addParameter("filterByUserIds", ids);
        query.addWheres(String.format(" AND (%1$s.user_id IN :filterByUserIds)", AS));
    }

    private void injectOrders(UserBorderMovementFilter filter, BaseQuery query) {
        if (filter == null) return;
        orderById(OrderDirection.DESK, query);
    }

    private void orderById(OrderDirection direction, BaseQuery query) {
        if (direction == null) return;
        query.addOrders(String.format("%1$s.id", AS), direction);
    }

}
