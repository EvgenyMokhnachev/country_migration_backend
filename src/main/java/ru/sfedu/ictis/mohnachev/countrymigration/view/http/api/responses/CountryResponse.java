package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.country.Country;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryResponse {
    private Long id;
    private String name;
    private String code;

    public CountryResponse(Country country) {
        this.id = country.getId();
        this.name = country.getName();
        this.code = country.getCode();
    }
}
