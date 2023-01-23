package ru.sfedu.ictis.mohnachev.countrymigration.domain.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.BaseGetListFilter;

import java.util.List;

@Data
@EqualsAndHashCode
public class UserFilter extends BaseGetListFilter<UserFilter> {
    private List<Long> ids;
    private List<String> emails;

    public static UserFilter create() {
        return new UserFilter();
    }

    public UserFilter byId(Long userId) {
        this.ids = List.of(userId);
        return this;
    }

    public UserFilter byEmail(String email) {
        this.emails = List.of(email);
        return this;
    }

}
