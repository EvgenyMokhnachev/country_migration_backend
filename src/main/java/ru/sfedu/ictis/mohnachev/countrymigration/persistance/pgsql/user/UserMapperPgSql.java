package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.user;

import org.springframework.stereotype.Component;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.user.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperPgSql {

    public User map(UserPgSql item) {
        if (item == null) return null;
        return new User(
                item.getId(),
                item.getEmail(),
                item.getPass()
        );
    }

    public UserPgSql mapToDb(User item) {
        if (item == null) return null;
        return new UserPgSql(
                item.getId(),
                item.getEmail(),
                item.getPass()
        );
    }

    public List<User> map(List<UserPgSql> items) {
        if (items == null) return null;
        return items.stream().map(this::map).collect(Collectors.toList());
    }

    public List<UserPgSql> mapToDb(List<User> items) {
        if (items == null) return null;
        return items.stream().map(this::mapToDb).collect(Collectors.toList());
    }

}
