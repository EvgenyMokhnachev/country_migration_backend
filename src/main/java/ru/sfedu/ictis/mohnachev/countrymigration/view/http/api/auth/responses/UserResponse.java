package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.auth.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.Auth;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.user.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;

    public UserResponse(User data) {
        this.id = data.getId();
        this.email = data.getEmail();
    }
}
