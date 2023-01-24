package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.country.Country;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.country.CountryFilter;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.country.CountryRepository;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.CountryGetRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses.CountryResponse;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses.PaginationResponse;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @PostMapping( "/country/get")
    private @ResponseBody PaginationResponse<CountryResponse> get(
            @RequestBody CountryGetRequest request
    ) {
        CountryFilter filter = CountryFilter.create();
        Pagination<Country> countryPagination = countryRepository.get(filter);
        List<CountryResponse> countryResponses = countryPagination.getList()
                .stream()
                .map(CountryResponse::new)
                .toList();
        return new PaginationResponse<>(countryResponses, null);
    }

}
