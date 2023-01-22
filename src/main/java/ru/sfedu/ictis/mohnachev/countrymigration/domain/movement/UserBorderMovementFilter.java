package ru.sfedu.ictis.mohnachev.countrymigration.domain.movement;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class UserBorderMovementFilter {
    private List<Long> userIds;

    public static UserBorderMovementFilter create() {
        return new UserBorderMovementFilter();
    }

    public UserBorderMovementFilter byUserId(Long userId) {
        this.userIds = List.of(userId);
        return this;
    }

}
