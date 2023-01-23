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
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.auth.requests.AuthRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.auth.responses.AuthResponse;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthControllerTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void fullAuthTest() throws Exception {
        String email = UUID.randomUUID().toString() + "@sfedu.ru";
        String pass = UUID.randomUUID().toString();

        AuthResponse firstLogin = this.restTemplate
                .postForObject(
                        "http://localhost:" + port + "/auth",
                        new AuthRequest(email, pass),
                        AuthResponse.class
                );
        Assertions.assertNotNull(firstLogin);
        Assertions.assertNotNull(firstLogin.getUserId());
        Assertions.assertNotEquals(0L, firstLogin.getUserId());
        Assertions.assertNotNull(firstLogin.getToken());
        Assertions.assertFalse(firstLogin.getToken().isBlank());





        ResponseEntity<AuthResponse> secondAuth = this.restTemplate
                .postForEntity(
                        "http://localhost:" + port + "/auth",
                        new AuthRequest(email, pass + "invalid"),
                        AuthResponse.class
                );
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, secondAuth.getStatusCode());





        AuthResponse thirdAuth = this.restTemplate
                .postForObject(
                        "http://localhost:" + port + "/auth",
                        new AuthRequest(email, pass),
                        AuthResponse.class
                );
        Assertions.assertEquals(firstLogin.getUserId(), thirdAuth.getUserId());
        Assertions.assertNotNull(firstLogin.getToken());
        Assertions.assertFalse(firstLogin.getToken().isBlank());
    }

}
