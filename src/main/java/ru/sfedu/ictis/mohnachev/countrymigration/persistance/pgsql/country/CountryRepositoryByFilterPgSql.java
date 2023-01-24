package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.country;

import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.country.CountryFilter;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.BasePgSqlRepository;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.BaseQuery;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.OrderDirection;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;

import java.util.List;

@Repository
public class CountryRepositoryByFilterPgSql extends BasePgSqlRepository<CountryPgSql, CountryFilter> {

    private static final String FROM = "cmp.countries";
    private static final String AS = "c";

    @Override
    public Pagination<CountryPgSql> getByFilter(CountryFilter filter) {
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

        injectOrders(filter, baseQuery);

        String sql = buildNativeSqlQuery(baseQuery);
        Query query = entityManager.createNativeQuery(sql, CountryPgSql.class);
        injectParameters(query, baseQuery.getParameters());
        List<CountryPgSql> items = query.getResultList();

        return new Pagination<>(items, getTotal(baseQuery));
    }

    private void filterByIds(List<Long> ids, BaseQuery query) {
        if (ids == null || ids.isEmpty()) return;
        query.addParameter("filterByIds", ids);
        query.addWheres(String.format(" AND (%1$s.id IN :filterByIds)", AS));
    }

    private void injectOrders(CountryFilter filter, BaseQuery query) {
        if (filter == null) return;
        orderById(OrderDirection.DESK, query);
    }

    private void orderById(OrderDirection direction, BaseQuery query) {
        if (direction == null) return;
        query.addOrders(String.format("%1$s.id", AS), direction);
    }

}
