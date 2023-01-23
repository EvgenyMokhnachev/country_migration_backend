package ru.sfedu.ictis.mohnachev.countrymigration.api.movement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import ru.sfedu.ictis.mohnachev.countrymigration.api.auth.AuthGenerator;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.Auth;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.MovementCreateRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.MovementDeleteRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.MovementsCalculateRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses.UserBorderMovementResponse;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses.UserTravelHistoryResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class UserBorderMovementCrudTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    AuthGenerator authGenerator;

    @Test
    public void fullAuthTest() {
        MovementCreateRequest testCreateRequest = new MovementCreateRequest();
        testCreateRequest.setCountryId(1L);
        testCreateRequest.setEnterDate(new Date());
        testCreateRequest.setExitDate(new Date());

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, this.restTemplate
                .postForEntity(
                        "http://localhost:" + port + "/movement/create",
                        testCreateRequest,
                        UserBorderMovementResponse.class
                ).getStatusCode());


        Auth auth = authGenerator.generateAuth();

        UserBorderMovementResponse createdMovementResponse = this.restTemplate
                .postForObject(
                        "http://localhost:" + port + "/movement/create",
                        testCreateRequest.setToken(auth.getToken()),
                        UserBorderMovementResponse.class
                );
        Assertions.assertNotNull(createdMovementResponse);
        Assertions.assertNotNull(createdMovementResponse.getId());


        MovementDeleteRequest movementDeleteRequest = new MovementDeleteRequest(
                createdMovementResponse.getId()
        );
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, this.restTemplate
                .postForEntity(
                        "http://localhost:" + port + "/movement/delete",
                        movementDeleteRequest,
                        Object.class
                ).getStatusCode());

        movementDeleteRequest.setToken(auth.getToken());
        Assertions.assertEquals(HttpStatus.OK, this.restTemplate
                .postForEntity(
                        "http://localhost:" + port + "/movement/delete",
                        movementDeleteRequest,
                        Object.class
                ).getStatusCode());
    }

    @Test
    public void calculateTestEndpoint() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.ROOT
        );

        Long countryId = 1L;

        Auth auth = authGenerator.generateAuth();

        Assertions.assertEquals(HttpStatus.OK, this.restTemplate
                .postForEntity(
                        "http://localhost:" + port + "/movement/create",
                        new MovementCreateRequest(
                                countryId,
                                sdf.parse("01/01/2022"),
                                sdf.parse("10/01/2022")
                        ).setToken(auth.getToken()),
                        UserBorderMovementResponse.class
                ).getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, this.restTemplate
                .postForEntity(
                        "http://localhost:" + port + "/movement/create",
                        new MovementCreateRequest(
                                countryId,
                                sdf.parse("10/01/2022"),
                                sdf.parse("20/01/2022")
                        ).setToken(auth.getToken()),
                        UserBorderMovementResponse.class
                ).getStatusCode());

        ResponseEntity<UserTravelHistoryResponse> travelHistoryResponse = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/movement/calculate",
                new MovementsCalculateRequest(
                                sdf.parse("05/01/2022"),
                                sdf.parse("15/01/2022")
                ).setToken(auth.getToken()),
                UserTravelHistoryResponse.class
        );
        Assertions.assertEquals(HttpStatus.OK, travelHistoryResponse.getStatusCode());

        UserTravelHistoryResponse travelHistory = travelHistoryResponse.getBody();
        Assertions.assertNotNull(travelHistory);
        Assertions.assertEquals(1, travelHistory.getTravelHistory().size());
        Assertions.assertNotNull(travelHistory.getTravelHistory().get(countryId));
        Assertions.assertEquals(11, travelHistory.getTravelHistory().get(countryId));
    }

}
