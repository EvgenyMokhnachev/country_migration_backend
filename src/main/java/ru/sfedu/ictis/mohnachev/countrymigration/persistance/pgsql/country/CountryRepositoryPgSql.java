package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.country.Country;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.country.CountryFilter;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.country.CountryRepository;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;

@Repository
public class CountryRepositoryPgSql implements CountryRepository {

    @Autowired
    CountryRepositoryByFilterPgSql filterPgSql;

    @Autowired
    CountryRepositoryJpaPgSql jpaPgSql;

    @Autowired
    CountryMapperPgSql mapperPgSql;

    @Override
    public Pagination<Country> get(CountryFilter filter) {
        Pagination<CountryPgSql> paginationData = filterPgSql.getByFilter(filter);
        return new Pagination<>(mapperPgSql.map(paginationData.getList()), paginationData.getTotal());
    }

    @Override
    public Country save(Country item) {
        return mapperPgSql.map(jpaPgSql.save(mapperPgSql.mapToDb(item)));
    }

}
