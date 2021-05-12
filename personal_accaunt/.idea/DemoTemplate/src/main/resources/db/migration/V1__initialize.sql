CREATE SCHEMA IF NOT EXISTS personalarea;
SET search_path TO personalArea;

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
id                    bigserial,
login                 VARCHAR(30) NOT NULL UNIQUE,
password              VARCHAR(80) NOT NULL,
PRIMARY KEY (id)
);

DROP TABLE IF EXISTS roles CASCADE ;
CREATE TABLE roles (
id                    serial,
name                  VARCHAR(50) NOT NULL,
PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users_roles;
CREATE TABLE users_roles (
user_id               INT NOT NULL,
role_id               INT NOT NULL,
PRIMARY KEY (user_id, role_id),
FOREIGN KEY (user_id)
    REFERENCES users (id),
FOREIGN KEY (role_id)
    REFERENCES roles (id)
);

INSERT INTO roles (name)
VALUES
('ROLE_CLIENT'), ('ROLE_ADMIN');

-- admin - пароль 100
--  user - пароль 200
INSERT INTO users (login, password)
VALUES
('admin','$2y$10$QE8NvW/g/FHIngi3kYXMwub3xuK1g9KDJbXToC/iNcXd1x2rFsMZC'),
('user','$2y$10$w6IfAC7/Tkqj6WdYkqdUIuaun7KmnFFzmybaOK1ZZ/IimwPY2Qgy6');

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(2, 1);

DROP TABLE IF EXISTS balance_type cascade;
CREATE TABLE balance_type (
id                    bigserial NOT NULL,
name                  VARCHAR(50) NOT NULL,
PRIMARY KEY (id)
);

DROP TABLE IF EXISTS balance;
CREATE TABLE balance(
id bigserial NOT NULL,
user_id bigint,
cash int NOT NULL,
bal_type_id INT,
date DATE not null,
PRIMARY KEY (id),
CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
CONSTRAINT fk_bal_id FOREIGN KEY (bal_type_id) REFERENCES balance_type(id));

INSERT INTO balance_type (id, name)
VALUES
(1, 'FREE'),
(2, 'FIXED');

INSERT INTO balance (user_id, cash, bal_type_id, date)
VALUES
(1, 16000, 1, CURRENT_DATE),
(1, 200, 2, CURRENT_DATE),
(1, 7000, 2, CURRENT_DATE),
(1, 23500, 1, CURRENT_DATE),
(1, 250000, 1, CURRENT_DATE),
(2, 3500, 2, CURRENT_DATE),
(2, 13500, 1, CURRENT_DATE),
(1, 600, 2, CURRENT_DATE),
(2, 2400, 1, CURRENT_DATE),
(1, 90000, 2, CURRENT_DATE),
(2, 137000, 1, CURRENT_DATE);




