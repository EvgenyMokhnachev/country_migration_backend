package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.country;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepositoryJpaPgSql extends CrudRepository<CountryPgSql, Long> {

}
