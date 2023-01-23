package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.auth;

import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.AuthFilter;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.BasePgSqlRepository;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.BaseQuery;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.OrderDirection;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;

import java.util.List;

@Repository
public class AuthRepositoryByFilterPgSql extends BasePgSqlRepository<AuthPgSql, AuthFilter> {

    private static final String FROM = "cmp.auths";
    private static final String AS = "a";

    @Override
    public Pagination<AuthPgSql> getByFilter(AuthFilter filter) {
        if (filter.getLimit() == null && filter.getTokens() != null && !filter.getTokens().isEmpty()) {
            filter.setLimit(filter.getTokens().size());
        }

        BaseQuery baseQuery = new BaseQuery(
                FROM, AS,
                String.format("%s.*", AS),
                String.format("%s.token", AS),
                filter.getCalculateTotal(),
                filter.getLimit(),
                filter.getOffset()
        );
        baseQuery.setOptimizedSelectForTotalQuery(String.format("%s.token", AS));

        filterByToken(filter.getTokens(), baseQuery);
        filterByUserIds(filter.getUserIds(), baseQuery);

        injectOrders(filter, baseQuery);

        String sql = buildNativeSqlQuery(baseQuery);
        Query query = entityManager.createNativeQuery(sql, AuthPgSql.class);
        injectParameters(query, baseQuery.getParameters());
        List<AuthPgSql> items = query.getResultList();

        return new Pagination<>(items, getTotal(baseQuery));
    }

    private void filterByToken(List<String> tokens, BaseQuery query) {
        if (tokens == null || tokens.isEmpty()) return;
        query.addParameter("filterByToken", tokens);
        query.addWheres(String.format(" AND (%1$s.token IN :filterByToken)", AS));
    }

    private void filterByUserIds(List<Long> userIds, BaseQuery query) {
        if (userIds == null || userIds.isEmpty()) return;
        query.addParameter("filterByUserIds", userIds);
        query.addWheres(String.format(" AND (%1$s.user_id IN :filterByUserIds)", AS));
    }

    private void injectOrders(AuthFilter filter, BaseQuery query) {
        if (filter == null) return;
        orderById(OrderDirection.DESK, query);
    }

    private void orderById(OrderDirection direction, BaseQuery query) {
        if (direction == null) return;
        query.addOrders(String.format("%1$s.user_id", AS), direction);
    }

}
