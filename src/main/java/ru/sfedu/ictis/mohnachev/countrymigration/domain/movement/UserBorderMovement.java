package ru.sfedu.ictis.mohnachev.countrymigration.domain.movement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBorderMovement {
    private Long countryId;
    private Long userId;
    private Date enterDate;
    private Date exitDate;
}
