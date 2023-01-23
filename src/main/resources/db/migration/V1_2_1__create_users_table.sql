create table cmp.users
(
    id bigserial not null,
    primary key (id),

    email varchar(255) not null unique,
    pass varchar(255) not null
);

CREATE INDEX users_email_index ON cmp.users(email);
