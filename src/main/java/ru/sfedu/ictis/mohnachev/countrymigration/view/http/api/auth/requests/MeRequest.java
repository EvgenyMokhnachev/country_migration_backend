package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.auth.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MeRequest extends Authenticated {

    public MeRequest(String token) {
        super(token);
    }
}
