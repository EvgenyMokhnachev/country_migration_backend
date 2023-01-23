package ru.sfedu.ictis.mohnachev.countrymigration.domain.movement;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.BaseGetListFilter;

import java.util.List;

@Data
@EqualsAndHashCode
public class UserBorderMovementFilter extends BaseGetListFilter<UserBorderMovementFilter> {
    private List<Long> ids;
    private List<Long> userIds;

    public static UserBorderMovementFilter create() {
        return new UserBorderMovementFilter();
    }

    public UserBorderMovementFilter byUserId(Long userId) {
        this.userIds = List.of(userId);
        return this;
    }

    public UserBorderMovementFilter byId(Long id) {
        this.ids = List.of(id);
        return this;
    }

}
