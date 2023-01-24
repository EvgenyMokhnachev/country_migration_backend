package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.country;

import org.springframework.stereotype.Component;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.country.Country;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryMapperPgSql {

    public Country map(CountryPgSql item) {
        if (item == null) return null;
        return new Country(
                item.getId(),
                item.getName(),
                item.getCode()
        );
    }

    public CountryPgSql mapToDb(Country item) {
        if (item == null) return null;
        return new CountryPgSql(
                item.getId(),
                item.getName(),
                item.getCode()
        );
    }

    public List<Country> map(List<CountryPgSql> items) {
        if (items == null) return null;
        return items.stream().map(this::map).collect(Collectors.toList());
    }

    public List<CountryPgSql> mapToDb(List<Country> users) {
        if (users == null) return null;
        return users.stream().map(this::mapToDb).collect(Collectors.toList());
    }

}
