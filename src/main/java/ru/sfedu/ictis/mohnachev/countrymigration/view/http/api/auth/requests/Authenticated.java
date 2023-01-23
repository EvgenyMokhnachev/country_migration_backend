package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.auth.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Authenticated {
    private String token;
}
