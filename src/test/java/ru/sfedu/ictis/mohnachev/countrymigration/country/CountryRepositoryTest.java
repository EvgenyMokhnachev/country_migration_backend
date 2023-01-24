package ru.sfedu.ictis.mohnachev.countrymigration.country;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.country.Country;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.country.CountryFilter;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.country.CountryRepository;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.AuthRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses.AuthResponse;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class CountryRepositoryTest {

    @Autowired
    CountryRepository countryRepository;

    @Test
    public void getAllCountries() {
        Pagination<Country> countryPagination = countryRepository.get(CountryFilter.create());
        Assertions.assertFalse(countryPagination.isEmpty());
    }

    @Test
    public void saveNewCountry() {
        Country country = Assertions.assertDoesNotThrow(() ->
                countryRepository.save(new Country(null, "Test", "TES"))
        );
        Assertions.assertNotNull(country);
    }

}
