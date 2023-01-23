package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.auth;

import org.springframework.stereotype.Component;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.Auth;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthMapperPgSql {

    public Auth map(AuthPgSql item) {
        if (item == null) return null;
        return new Auth(
                item.getToken(),
                item.getUserId()
        );
    }

    public AuthPgSql mapToDb(Auth item) {
        if (item == null) return null;
        return new AuthPgSql(
                item.getToken(),
                item.getUserId()
        );
    }

    public List<Auth> map(List<AuthPgSql> items) {
        if (items == null) return null;
        return items.stream().map(this::map).collect(Collectors.toList());
    }

    public List<AuthPgSql> mapToDb(List<Auth> users) {
        if (users == null) return null;
        return users.stream().map(this::mapToDb).collect(Collectors.toList());
    }

}
