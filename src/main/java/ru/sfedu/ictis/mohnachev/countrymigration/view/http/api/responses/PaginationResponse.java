package ru.sfedu.ictis.mohnachev.countrymigration.view.http.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse<T> implements Serializable {
    private List<T> items;
    private Long total;
}
