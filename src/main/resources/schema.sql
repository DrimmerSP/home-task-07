DROP TABLE IF EXISTS order_films;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS film_directors;
DROP TABLE IF EXISTS films;
DROP TABLE IF EXISTS directors;

CREATE TABLE directors
(
    id           BIGINT NOT NULL PRIMARY KEY,
    created_by   VARCHAR(255),
    created_when TIMESTAMP(6),
    deleted_by   VARCHAR(255),
    deleted_when TIMESTAMP(6),
    is_deleted   BOOLEAN DEFAULT FALSE,
    director_fio VARCHAR(255),
    position     INTEGER,
    description  VARCHAR(255)
);

ALTER TABLE directors OWNER TO postgres;

CREATE TABLE films
(
    id           BIGINT       NOT NULL PRIMARY KEY,
    created_by   VARCHAR(255),
    created_when TIMESTAMP(6),
    deleted_by   VARCHAR(255),
    deleted_when TIMESTAMP(6),
    is_deleted   BOOLEAN DEFAULT FALSE,
    country      VARCHAR(255),
    description  VARCHAR(255),
    genre        VARCHAR(255),
    premier_date DATE,
    title        VARCHAR(255) NOT NULL,
    price        BIGINT
);

ALTER TABLE films OWNER TO postgres;

CREATE TABLE film_directors
(
    film_id     BIGINT NOT NULL CONSTRAINT fk65wtmwe1xu57etq8batlbdi9b REFERENCES films,
    director_id BIGINT NOT NULL CONSTRAINT fkal3rkki5i99o7dbm8lc24x3hh REFERENCES directors
);

ALTER TABLE film_directors OWNER TO postgres;

CREATE TABLE role
(
    id          BIGSERIAL PRIMARY KEY,
    description VARCHAR(255),
    title       VARCHAR(255)
);

ALTER TABLE role OWNER TO postgres;

CREATE TABLE users
(
    id                    BIGINT       NOT NULL PRIMARY KEY,
    created_by            VARCHAR(255),
    created_when          TIMESTAMP(6),
    deleted_by            VARCHAR(255),
    deleted_when          TIMESTAMP(6),
    is_deleted            BOOLEAN DEFAULT FALSE,
    address               VARCHAR(255) NOT NULL,
    birth_date            DATE         NOT NULL,
    change_password_token VARCHAR(255),
    email                 VARCHAR(255) NOT NULL CONSTRAINT uniqueemail UNIQUE,
    first_name            VARCHAR(255) NOT NULL,
    last_name             VARCHAR(255) NOT NULL,
    login                 VARCHAR(255) NOT NULL CONSTRAINT uniquelogin UNIQUE,
    middle_name           VARCHAR(255) NOT NULL,
    password              VARCHAR(255) NOT NULL,
    phone                 VARCHAR(255) NOT NULL,
    role_id               BIGINT       NOT NULL CONSTRAINT fk_users_roles REFERENCES role
);

ALTER TABLE users OWNER TO postgres;

CREATE TABLE orders
(
    id           BIGINT NOT NULL PRIMARY KEY,
    created_by   VARCHAR(255),
    created_when TIMESTAMP(6),
    deleted_by   VARCHAR(255),
    deleted_when TIMESTAMP(6),
    is_deleted   BOOLEAN DEFAULT FALSE,
    is_purchase  BOOLEAN,
    is_return    BOOLEAN,
    rent_from    DATE   NOT NULL,
    rent_to      DATE   NOT NULL,
    user_id      BIGINT NOT NULL CONSTRAINT fk_order_user REFERENCES users
);

ALTER TABLE orders OWNER TO postgres;

CREATE TABLE order_films
(
    order_id BIGINT NOT NULL CONSTRAINT fk4rqv9exw1iap8t0o8ng74r9l2 REFERENCES orders,
    film_id  BIGINT NOT NULL CONSTRAINT uk_arl2l32nyyv3wiqyak5q4ie46 UNIQUE CONSTRAINT fk75ttuewhpgumojjdngnogbx24 REFERENCES films
);

ALTER TABLE order_films OWNER TO postgres;

