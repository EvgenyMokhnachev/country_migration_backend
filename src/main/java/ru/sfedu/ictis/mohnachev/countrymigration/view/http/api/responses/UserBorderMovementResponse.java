package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.UserBorderMovement;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBorderMovementResponse {
    private Long id;
    private Long countryId;
    private Long userId;
    private Date enterDate;
    private Date exitDate;

    public UserBorderMovementResponse(UserBorderMovement item) {
        this.id = item.getId();
        this.countryId = item.getCountryId();
        this.userId = item.getUserId();
        this.enterDate = item.getEnterDate();
        this.exitDate = item.getExitDate();
    }
}
