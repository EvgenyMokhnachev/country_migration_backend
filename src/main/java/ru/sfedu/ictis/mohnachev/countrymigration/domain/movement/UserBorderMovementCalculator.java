package ru.sfedu.ictis.mohnachev.countrymigration.domain.movement;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UserBorderMovementCalculator {

    @Autowired
    private UserBorderMovementRepository userBorderMovementRepository;

    public Map<Long, Integer> getTravelHistory(Long userId) {
        return getTravelHistory(userId, null, null);
    }

    private List<UserBorderMovement> normalizeTravelsListByDate(
            List<UserBorderMovement> travels
    ) {
        List<UserBorderMovement> result = new ArrayList<>(travels);

        UserBorderMovement movementA = null;
        UserBorderMovement movementB = null;

        for (UserBorderMovement movementX : result) {
            if (movementA != null) break;

            for (UserBorderMovement movementY : result) {
                if (movementX == movementY) continue;
                if (!movementX.getCountryId().equals(movementY.getCountryId())) continue;

                Date enterDateY = movementY.getEnterDate();
                Date exitDateX = (movementX.getExitDate() != null)
                        ? movementX.getExitDate()
                        : new Date();

                long daysDiff = TimeUnit.DAYS.convert(
                        exitDateX.getTime() - enterDateY.getTime(),
                        TimeUnit.MILLISECONDS
                );
                if (daysDiff != 0) continue;

                movementA = movementX;
                movementB = movementY;
                break;
            }
        }

        if (movementA != null) {
            result.remove(movementA);
            result.remove(movementB);

            Date enterDate = (movementA.getEnterDate().before(movementB.getEnterDate()))
                    ? movementA.getEnterDate()
                    : movementB.getEnterDate();
            Date exitDate = (movementA.getExitDate().after(movementB.getExitDate()))
                    ? movementA.getExitDate()
                    : movementB.getExitDate();

            result.add(new UserBorderMovement(
                    movementA.getCountryId(),
                    movementA.getUserId(),
                    enterDate,
                    exitDate
            ));
            result = normalizeTravelsListByDate(result);
        }

        return result;
    }

    public Map<Long, Integer> getTravelHistory(
            Long userId,
            @Nullable Date from, @Nullable Date to
    ) {
        Map<Long, Integer> countryDaysMap = new HashMap<>();

        List<UserBorderMovement> userBorderMovements = normalizeTravelsListByDate(
                userBorderMovementRepository.get(
                        UserBorderMovementFilter.create().byUserId(userId)
                )
        );

        for (UserBorderMovement userBorderMovement : userBorderMovements) {
            Date enterDate = userBorderMovement.getEnterDate();
            Date exitDate = (userBorderMovement.getExitDate() != null)
                    ? userBorderMovement.getExitDate()
                    : new Date();

            if (from != null && exitDate.before(from)) {
                continue;
            }

            if (to != null && enterDate.after(to)) {
                continue;
            }

            long diffInMs = Math.abs(exitDate.getTime() - enterDate.getTime());
            long diffInDays = TimeUnit.DAYS.convert(diffInMs, TimeUnit.MILLISECONDS) + 1;

            Integer currentCountryDays = countryDaysMap.get(userBorderMovement.getCountryId());
            if (currentCountryDays == null) {
                currentCountryDays = 0;
            }

            countryDaysMap.put(
                    userBorderMovement.getCountryId(),
                    Long.valueOf(currentCountryDays + diffInDays).intValue()
            );
        }

        return countryDaysMap;
    }

}
