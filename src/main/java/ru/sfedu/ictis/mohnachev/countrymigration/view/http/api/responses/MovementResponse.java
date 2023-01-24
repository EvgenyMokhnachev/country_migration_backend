package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.UserBorderMovement;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementResponse {
    private Long id;
    private Long countryId;
    private Long userId;
    private Date enterDate;
    private Date exitDate;

    public MovementResponse(UserBorderMovement movement) {
        this.id = movement.getId();
        this.countryId = movement.getCountryId();
        this.userId = movement.getUserId();
        this.enterDate = movement.getEnterDate();
        this.exitDate = movement.getExitDate();
    }
}
