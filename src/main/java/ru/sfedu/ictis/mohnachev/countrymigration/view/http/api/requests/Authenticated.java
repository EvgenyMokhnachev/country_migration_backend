package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Authenticated<T> {
    private String token;

    public T setToken(String token) {
        this.token = token;
        return (T) this;
    }
}
