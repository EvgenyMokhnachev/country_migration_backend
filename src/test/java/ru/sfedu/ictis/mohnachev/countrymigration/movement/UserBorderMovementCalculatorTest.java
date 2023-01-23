package ru.sfedu.ictis.mohnachev.countrymigration.movement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.UserBorderMovement;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.UserBorderMovementFilter;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.UserBorderMovementRepository;
import ru.sfedu.ictis.mohnachev.countrymigration.domain.movement.UserBorderMovementCalculator;
import ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.Pagination;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@SpringBootTest(classes = UserBorderMovementCalculatorTestConfig.class)
class UserBorderMovementCalculatorTest {

    @Mock
    UserBorderMovementRepository userBorderMovementRepository;

    @InjectMocks
    UserBorderMovementCalculator userBorderMovementService;

    @Test
    void getCountryMovementCalculationTest() throws ParseException {
		Long testUserId = 1L;
        SimpleDateFormat sdf = new SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.ROOT
        );

        UserBorderMovementFilter userBorderMovementFilter
                = UserBorderMovementFilter.create().byUserId(testUserId);

        Mockito.lenient().when(
                userBorderMovementRepository.get(userBorderMovementFilter)
        ).thenReturn(
                new Pagination<>(List.of(
                        new UserBorderMovement(1L, testUserId,
                                sdf.parse("01/01/2022"),
                                sdf.parse("01/01/2022")),
						new UserBorderMovement(1L, testUserId,
                                sdf.parse("01/01/2022"),
                                sdf.parse("10/01/2022")),

                        new UserBorderMovement(2L, testUserId,
                                sdf.parse("10/01/2022"),
                                sdf.parse("15/01/2022")),

						new UserBorderMovement(1L, testUserId,
                                sdf.parse("15/01/2022"),
                                sdf.parse("20/01/2022")),
                        new UserBorderMovement(1L, testUserId,
                                sdf.parse("20/01/2022"),
                                sdf.parse("22/01/2022")),

                        new UserBorderMovement(3L, testUserId,
                                sdf.parse("22/01/2022"),
                                sdf.parse("22/01/2022"))
                ), 6L)
        );

        Map<Long, Integer> travelHistory
                = userBorderMovementService.getTravelHistory(testUserId);

		Assertions.assertNotNull(travelHistory);
        Assertions.assertEquals(travelHistory.size(), 3);
        Assertions.assertEquals(travelHistory.get(1L), 18);
        Assertions.assertEquals(travelHistory.get(2L), 6);
        Assertions.assertEquals(travelHistory.get(3L), 1);
    }

}
