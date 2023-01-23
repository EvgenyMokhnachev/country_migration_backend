package ru.sfedu.ictis.mohnachev.countrymigration.domain.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.BaseGetListFilter;

import java.util.List;

@Data
@EqualsAndHashCode
public class AuthFilter extends BaseGetListFilter<AuthFilter> {
    private List<Long> userIds;
    private List<String> tokens;

    public static AuthFilter create() {
        return new AuthFilter();
    }

    public AuthFilter byUserId(Long userId) {
        this.userIds = List.of(userId);
        return this;
    }

    public AuthFilter byToken(String token) {
        this.tokens = List.of(token);
        return this;
    }

}
