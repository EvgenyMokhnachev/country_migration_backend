package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTravelHistoryResponse {
    private Map<Long, Integer> travelHistory;
}
