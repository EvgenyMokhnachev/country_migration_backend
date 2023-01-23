package ru.sfedu.ictis.mohnachev.countrymigration.api.auth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.AuthRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.MeRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses.AuthResponse;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses.UserResponse;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class MeControllerTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void meAuthenticationTest() {
        ResponseEntity<UserResponse> tryMeEndpoint = this.restTemplate
                .postForEntity(
                        "http://localhost:" + port + "/user/me",
                        new MeRequest(UUID.randomUUID().toString()),
                        UserResponse.class
                );
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, tryMeEndpoint.getStatusCode());


        String email = UUID.randomUUID().toString() + "@sfedu.ru";
        String pass = UUID.randomUUID().toString();
        AuthResponse firstLogin = this.restTemplate
                .postForObject(
                        "http://localhost:" + port + "/auth",
                        new AuthRequest(email, pass),
                        AuthResponse.class
                );




        UserResponse secondTryMeEndpoint = this.restTemplate
                .postForObject(
                        "http://localhost:" + port + "/user/me",
                        new MeRequest(firstLogin.getToken()),
                        UserResponse.class
                );
        Assertions.assertNotNull(secondTryMeEndpoint);
        Assertions.assertNotNull(secondTryMeEndpoint.getId());
        Assertions.assertNotEquals(0L, secondTryMeEndpoint.getId());
        Assertions.assertNotNull(secondTryMeEndpoint.getEmail());
        Assertions.assertFalse(secondTryMeEndpoint.getEmail().isBlank());
        Assertions.assertEquals(firstLogin.getUserId(), secondTryMeEndpoint.getId());
        Assertions.assertEquals(email, secondTryMeEndpoint.getEmail());
    }

}
