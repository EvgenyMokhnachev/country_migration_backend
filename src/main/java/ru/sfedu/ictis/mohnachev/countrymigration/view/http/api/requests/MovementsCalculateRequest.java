package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementsCalculateRequest extends Authenticated {
    private Date from;
    private Date to;
}
