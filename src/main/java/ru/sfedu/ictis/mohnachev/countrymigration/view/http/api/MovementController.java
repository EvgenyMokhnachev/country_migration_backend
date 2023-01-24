package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.Auth;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.exceptions.AuthNotFoundException;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.*;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.exceptions.UserBorderMovementNotFoundException;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.MovementCreateRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.MovementDeleteRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.MovementsGetRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses.*;

import java.util.List;

@RestController
public class MovementController extends AuthenticatedController {

    @Autowired
    UserBorderMovementCrudService crudService;

    @Autowired
    UserBorderMovementRepository userBorderMovementRepository;

    @Autowired
    UserBorderMovementCalculator calculator;

    @PostMapping( "/movement/get")
    private @ResponseBody PaginationResponse<MovementResponse> create(
            @RequestBody MovementsGetRequest request
    ) throws AuthNotFoundException {
        Auth auth = this.isAuthenticated(request);

        UserBorderMovementFilter filter = UserBorderMovementFilter.create();

        Pagination<UserBorderMovement> pagination = crudService.get(
                filter,
                auth.getUserId()
        );

        List<MovementResponse> movementResponses = pagination.getList()
                .stream()
                .map(MovementResponse::new)
                .toList();

        return new PaginationResponse<>(movementResponses, pagination.getTotal());
    }

    @PostMapping( "/movement/create")
    private @ResponseBody UserBorderMovementResponse create(
            @RequestBody MovementCreateRequest request
    ) throws AuthNotFoundException {
        Auth auth = this.isAuthenticated(request);

        return new UserBorderMovementResponse(
                crudService.create(
                        auth.getUserId(),
                        request.getCountryId(),
                        request.getEnterDate(),
                        request.getExitDate()
                )
        );
    }

    @PostMapping( "/movement/delete")
    private @ResponseBody MovementDeleteResponse delete(
            @RequestBody MovementDeleteRequest request
    ) throws
            AuthNotFoundException,
            UserBorderMovementNotFoundException
    {
        Auth auth = this.isAuthenticated(request);

        crudService.delete(
                request.getMovementId(),
                auth.getUserId()
        );

        return new MovementDeleteResponse();
    }

    @PostMapping( "/movement/calculate")
    private @ResponseBody UserTravelHistoryResponse delete(
            @RequestBody MovementsGetRequest request
    ) throws
            AuthNotFoundException
    {
        Auth auth = this.isAuthenticated(request);

        return new UserTravelHistoryResponse(
                calculator.getTravelHistory(
                        auth.getUserId(),
                        request.getFrom(),
                        request.getTo()
                )
        );
    }

}
