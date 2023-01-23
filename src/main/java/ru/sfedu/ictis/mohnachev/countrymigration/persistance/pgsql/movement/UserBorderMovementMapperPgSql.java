package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.movement;

import org.springframework.stereotype.Component;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.UserBorderMovement;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserBorderMovementMapperPgSql {

    public UserBorderMovement map(UserBorderMovementPgSql item) {
        if (item == null) return null;
        return new UserBorderMovement(
                item.getId(),
                item.getCountryId(),
                item.getUserId(),
                item.getEnterDate(),
                item.getExitDate()
        );
    }

    public UserBorderMovementPgSql mapToDb(UserBorderMovement item) {
        if (item == null) return null;
        return new UserBorderMovementPgSql(
                item.getId(),
                item.getCountryId(),
                item.getUserId(),
                item.getEnterDate(),
                item.getExitDate()
        );
    }

    public List<UserBorderMovement> map(List<UserBorderMovementPgSql> items) {
        if (items == null) return null;
        return items.stream().map(this::map).collect(Collectors.toList());
    }

    public List<UserBorderMovementPgSql> mapToDb(List<UserBorderMovement> items) {
        if (items == null) return null;
        return items.stream().map(this::mapToDb).collect(Collectors.toList());
    }

}
