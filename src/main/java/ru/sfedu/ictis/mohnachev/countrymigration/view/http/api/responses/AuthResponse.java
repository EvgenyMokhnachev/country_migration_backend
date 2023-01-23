package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.Auth;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Long userId;
    private String token;

    public AuthResponse(Auth auth) {
        this.userId = auth.getUserId();
        this.token = auth.getToken();
    }
}
