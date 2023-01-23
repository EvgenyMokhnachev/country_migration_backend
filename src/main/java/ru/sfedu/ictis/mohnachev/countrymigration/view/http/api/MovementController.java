package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.Auth;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.auth.exceptions.AuthNotFoundException;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.UserBorderMovementCalculator;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.UserBorderMovementCrudService;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.exceptions.UserBorderMovementNotFoundException;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.MovementCreateRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.MovementDeleteRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests.MovementsCalculateRequest;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses.UserBorderMovementResponse;
import ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses.UserTravelHistoryResponse;

@RestController
public class MovementController extends AuthenticatedController {

    @Autowired
    UserBorderMovementCrudService crudService;

    @Autowired
    UserBorderMovementCalculator calculator;

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
    private void delete(
            @RequestBody MovementDeleteRequest request
    ) throws
            AuthNotFoundException,
            UserBorderMovementNotFoundException
    {
        Auth auth = this.isAuthenticated(request);

        crudService.delete(
                auth.getUserId(),
                request.getMovementId()
        );
    }

    @PostMapping( "/movement/calculate")
    private @ResponseBody UserTravelHistoryResponse delete(
            @RequestBody MovementsCalculateRequest request
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
