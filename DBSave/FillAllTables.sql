INSERT INTO public.directors (id, director_fio, position)
VALUES (1, 'Sagar S.B.', 1);
INSERT INTO public.directors (id, director_fio, position)
VALUES (2, 'Narione D.V.', 2);
INSERT INTO public.directors (id, director_fio, position)
VALUES (3, 'Leiblez Z.P.', 3);

INSERT INTO public.films (id, country, genre, premier_date, title)
VALUES (1, 'Babve', 'sport', '2020-06-20', 'Night runing');
INSERT INTO public.films (id, country, genre, premier_date, title)
VALUES (2, 'Antaria', 'romantic', '2013-06-08', 'Flowers in the rise');
INSERT INTO public.films (id, country, genre, premier_date, title)
VALUES (3, 'Iria', 'geographic', '2023-05-10', 'Taiga');

INSERT INTO public.role (id, description, title)
VALUES (1, 'Admin', 'admin');
INSERT INTO public.role (id, description, title)
VALUES (2, 'Director', 'director');
INSERT INTO public.role (id, description, title)
VALUES (3, 'User', 'user');

INSERT INTO public.users (id, address, birth_date, created_when, email, first_name, last_name, login, middle_name,
                          password, phone, role_id)
VALUES (3, 'Savanna, Sunrise 29, r. 20', '1998-06-06', '2022-09-10', 'savann@mailiz.ru', 'Dima', 'Zimenovich', 'Dima',
        'Borisovich', 'dima', '+79881234568', 3);
INSERT INTO public.users (id, address, birth_date, created_when, email, first_name, last_name, login, middle_name,
                          password, phone, role_id)
VALUES (2, 'Novorechensk, Rechnaya 87, r.1', '1985-06-12', '2015-06-03', 'parahod@mailiz.ru', 'Ruslan', 'Baristov',
        'Ruslan', 'Yurievich', 'ruslan', '+79771234569', 2);
INSERT INTO public.users (id, address, birth_date, created_when, email, first_name, last_name, login, middle_name,
                          password, phone, role_id)
VALUES (1, 'Mnogorechensk, Pobedy 56, r.5', '2000-06-10', '2023-06-10', 'reka@mailiz.ru', 'Vasya', 'Skobar', 'Admin',
        'Petrovich', 'admin', '+79991234567', 1);

INSERT INTO public.orders (id, purchase, rent_from, rent_to, user_id)
VALUES (1, true, '2023-06-10', '2023-06-20', 2);
INSERT INTO public.orders (id, purchase, rent_from, rent_to, user_id)
VALUES (2, true, '2023-06-08', '2023-06-30', 1);
INSERT INTO public.orders (id, purchase, rent_from, rent_to, user_id)
VALUES (3, false, '2023-06-18', '2023-06-19', 3);
INSERT INTO public.orders (id, purchase, rent_from, rent_to, user_id)
VALUES (4, true, '2023-06-17', '2023-06-18', 2);

INSERT INTO public.film_directors (film_id, director_id)
VALUES (1, 3);
INSERT INTO public.film_directors (film_id, director_id)
VALUES (2, 2);
INSERT INTO public.film_directors (film_id, director_id)
VALUES (3, 1);

INSERT INTO public.order_films (order_id, film_id)
VALUES (1, 3);
INSERT INTO public.order_films (order_id, film_id)
VALUES (2, 2);
INSERT INTO public.order_films (order_id, film_id)
VALUES (3, 1);

