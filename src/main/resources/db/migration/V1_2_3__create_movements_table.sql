create table cmp.movements
(
    id bigserial not null,
    primary key (id),

    country_id bigint not null,
    user_id bigint not null,
    enter_date timestamp not null,
    exit_date timestamp
);

CREATE INDEX movements_user_id_index ON cmp.movements(user_id);
