package ru.sfedu.ictis.mohnachev.countrymigration.domain.country;

import lombok.Getter;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.BaseGetListFilter;

import java.util.List;

@Getter
public class CountryFilter extends BaseGetListFilter<CountryFilter> {
    private List<Long> ids;

    public static CountryFilter create() {
        return new CountryFilter();
    }

    public CountryFilter byIds(List<Long> ids) {
        this.ids = ids;
        return this;
    }
}
