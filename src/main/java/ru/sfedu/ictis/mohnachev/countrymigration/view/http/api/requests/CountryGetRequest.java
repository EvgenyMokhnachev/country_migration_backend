package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryGetRequest extends Authenticated<CountryGetRequest> {
    private List<Long> ids;
}
