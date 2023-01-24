package ru.sfedu.ictis.mohnachev.countrymigration.domain.country;

import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;

public interface CountryRepository {
    Pagination<Country> get(CountryFilter filter);
    Country save(Country item);
}
