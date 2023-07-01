INSERT INTO public.directors (director_fio, position)
VALUES ('Sagar S.B.', 1),
       ('Narione D.V.', 2),
       ('Leiblez Z.P.', 3);

INSERT INTO public.films (country, genre, premier_date, title, description)
VALUES ('TANZANIA', 'COMEDY', '2020-06-20', 'Night runing', 'calculation pinch never size'),
       ('UGANDA', 'ADVENTURE', '2013-06-08', 'Flowers in the rise', 'depend loose foot native'),
       ('VIETNAM', 'DETECTIVE', '2023-05-10', 'Taiga', 'number composition flour spit');

INSERT INTO public.role (description, title)
VALUES ('Admin', 'admin'),
       ('Director', 'director'),
       ('User', 'user');

INSERT INTO public.users (address, birth_date, created_when, email, first_name, last_name, login, middle_name, password, phone, role_id)
VALUES ('Savanna, Sunrise 29, r. 20', '1998-06-06', '2022-09-10', 'savann@mailiz.ru', 'Dima', 'Zimenovich', 'Dima', 'Borisovich', 'dima', '+79881234568', 3),
       ('Novorechensk, Rechnaya 87, r.1',
        '1985-06-12',
        '2015-06-03',
        'parahod@mailiz.ru',
        'Ruslan',
        'Baristov',
        'Ruslan',
        'Yurievich',
        'ruslan',
        '+79771234569',
        2),
       ('Mnogorechensk, Pobedy 56, r.5', '2000-06-10', '2023-06-10', 'reka@mailiz.ru', 'Vasya', 'Skobar', 'Vasya', 'Petrovich', 'Vasya', '+79991234567', 1),
       ('asdfasdfasdf',
        '2021-06-02',
        '2023-07-02 00:16:37.901756',
        'stealth@stealth.com',
        'stealth',
        'stealth',
        'stealth',
        'stealth',
        '$2a$10$/FAchAsd6gAf1Nz.8rUwduMzOpq7RfhxSdAW0LJNwFSOLeuoD43wm',
        '89150686205',
        1);


INSERT INTO public.orders (purchase, rent_from, rent_to, user_id)
VALUES (TRUE, '2023-06-10', '2023-06-20', 2),
       (TRUE, '2023-06-08', '2023-06-30', 1),
       (FALSE, '2023-06-18', '2023-06-19', 3),
       (TRUE, '2023-06-17', '2023-06-18', 2);

INSERT INTO public.film_directors (film_id, director_id)
VALUES (1, 3),
       (2, 2),
       (3, 1);

INSERT INTO public.order_films (order_id, film_id)
VALUES (1, 3),
       (2, 2),
       (3, 1);