CREATE SEQUENCE cmp.countries_id_seq;

CREATE TABLE cmp.countries
(
    id BIGINT NOT NULL DEFAULT nextval('cmp.countries_id_seq'),
    PRIMARY KEY (id),

    name VARCHAR(255) NOT NULL UNIQUE,
    code VARCHAR(255) NOT NULL UNIQUE
);

CREATE INDEX countries_code_index ON cmp.countries(code);

INSERT INTO cmp.countries(id, name, code) VALUES (nextval('cmp.countries_id_seq'), 'Россия', 'RUS');
INSERT INTO cmp.countries(id, name, code) VALUES (nextval('cmp.countries_id_seq'), 'Беларусь', 'BEL');
INSERT INTO cmp.countries(id, name, code) VALUES (nextval('cmp.countries_id_seq'), 'Грузия', 'GEL');
INSERT INTO cmp.countries(id, name, code) VALUES (nextval('cmp.countries_id_seq'), 'Казахстан', 'KAZ');
INSERT INTO cmp.countries(id, name, code) VALUES (nextval('cmp.countries_id_seq'), 'Тайланд', 'TAI');
INSERT INTO cmp.countries(id, name, code) VALUES (nextval('cmp.countries_id_seq'), 'Болгария', 'BOL');
INSERT INTO cmp.countries(id, name, code) VALUES (nextval('cmp.countries_id_seq'), 'Черногория', 'MON');
INSERT INTO cmp.countries(id, name, code) VALUES (nextval('cmp.countries_id_seq'), 'Сербия', 'SER');
INSERT INTO cmp.countries(id, name, code) VALUES (nextval('cmp.countries_id_seq'), 'Турция', 'TUR');
