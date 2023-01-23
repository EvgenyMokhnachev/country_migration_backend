package ru.sfedu.ictis.mohnachev.countrymigration.persistance.pgsql.movement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "cmp", name = "movements")
public class UserBorderMovementPgSql {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_id", nullable = false)
    private Long countryId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "enter_date", nullable = false)
    private Date enterDate;

    @Column(name = "exit_date")
    private Date exitDate;

}
