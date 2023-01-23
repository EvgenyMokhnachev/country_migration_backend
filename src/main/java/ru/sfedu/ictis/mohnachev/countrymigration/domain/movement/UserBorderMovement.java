package ru.sfedu.ictis.mohnachev.countrymigration.domain.movement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBorderMovement {
    private Long id;
    private Long countryId;
    private Long userId;
    private Date enterDate;
    private Date exitDate;

    public UserBorderMovement(Long countryId, Long userId, Date enterDate, Date exitDate) {
        this.countryId = countryId;
        this.userId = userId;
        this.enterDate = enterDate;
        this.exitDate = exitDate;
    }
}
