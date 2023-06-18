CREATE TABLE directors
(
    id           SERIAL
        PRIMARY KEY,
    director_fio VARCHAR(255),
    position     INTEGER
);

ALTER TABLE directors
    OWNER TO postgres;

CREATE TABLE role
(
    id          SERIAL
        PRIMARY KEY,
    description VARCHAR(255),
    title       VARCHAR(255)
);

ALTER TABLE role
    OWNER TO postgres;

CREATE TABLE users
(
    id           SERIAL
        PRIMARY KEY,
    address      VARCHAR(255),
    birth_date   DATE,
    created_when DATE         NOT NULL,
    email        VARCHAR(255) NOT NULL,
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    login        VARCHAR(255) NOT NULL,
    middle_name  VARCHAR(255),
    password     VARCHAR(255) NOT NULL,
    phone        VARCHAR(255) NOT NULL,
    role_id      INTEGER      NOT NULL
        CONSTRAINT fk_users_roles
            REFERENCES role
);

ALTER TABLE users
    OWNER TO postgres;

CREATE TABLE orders
(
    id          SERIAL
        PRIMARY KEY,
    purchase    BOOLEAN,
    rent_date   TIMESTAMP(6) NOT NULL,
    rent_period TIMESTAMP(6) NOT NULL,
    user_id     INTEGER      NOT NULL
        CONSTRAINT fk_order_user
            REFERENCES users
);

ALTER TABLE orders
    OWNER TO postgres;

CREATE TABLE films
(
    id           SERIAL
        PRIMARY KEY,
    country      VARCHAR(255),
    genre        VARCHAR(255),
    premier_date DATE,
    title        VARCHAR(255) NOT NULL,
    film_id      INTEGER      NOT NULL
        CONSTRAINT fk_order_film
            REFERENCES orders
);

ALTER TABLE films
    OWNER TO postgres;

CREATE TABLE film_directors
(
    film_id     INTEGER NOT NULL
        CONSTRAINT fk65wtmwe1xu57etq8batlbdi9b
            REFERENCES films,
    director_id INTEGER NOT NULL
        CONSTRAINT fkal3rkki5i99o7dbm8lc24x3hh
            REFERENCES directors
);

ALTER TABLE film_directors
    OWNER TO postgres;

