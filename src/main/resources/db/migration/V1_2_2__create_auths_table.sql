create table cmp.auths
(
    token varchar(255) not null,
    primary key (token),

    user_id bigint not null
);

CREATE INDEX auths_user_id_index ON cmp.auths(user_id);
