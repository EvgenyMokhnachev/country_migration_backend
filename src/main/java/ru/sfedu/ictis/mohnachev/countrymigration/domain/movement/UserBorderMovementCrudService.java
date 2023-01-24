package ru.sfedu.ictis.mohnachev.countrymigration.domain.movement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.exceptions.UserBorderMovementNotFoundException;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;

import java.util.Date;

@Service
public class UserBorderMovementCrudService {

    @Autowired
    UserBorderMovementRepository repository;

    public Pagination<UserBorderMovement> get(UserBorderMovementFilter filter, @Nullable Long userId) {
        if (userId != null) {
            filter.byUserId(userId);
        }

        return repository.get(filter);
    }

    public UserBorderMovement create(Long userId, Long countryId, Date enterDate, @Nullable Date exitDate) {
        // todo validate countryId
        // todo validate enterDate < exitDate
        // todo validate user history - stay in two countries
        UserBorderMovement movement = new UserBorderMovement(countryId, userId, enterDate, exitDate);
        return repository.save(movement);
    }

    public void delete(Long id, @Nullable Long userId) throws
            UserBorderMovementNotFoundException
    {
        UserBorderMovementFilter filter = UserBorderMovementFilter.create()
                .byId(id);

        if (userId != null) {
            filter.byUserId(userId);
        }

        if (repository.get(filter).isEmpty()) {
            throw new UserBorderMovementNotFoundException();
        }

        repository.delete(id);
    }

}
