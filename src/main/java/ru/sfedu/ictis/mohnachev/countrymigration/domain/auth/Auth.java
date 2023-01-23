package ru.sfedu.ictis.mohnachev.countrymigration.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    private String token;
    private Long userId;
}
