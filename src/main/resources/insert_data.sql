INSERT INTO public.directors (id, created_by, created_when, deleted_by, deleted_when, is_deleted, director_fio, position)
VALUES (1, 'SYSTEM', '2020-07-03 23:16:17.000000', NULL, NULL, FALSE, 'Sagar S.B.', 1),
       (2, 'SYSTEM', '2020-11-03 23:16:58.000000', NULL, NULL, FALSE, 'Narione D.V.', 2),
       (3, 'SYSTEM', '2019-06-03 23:17:23.000000', NULL, NULL, FALSE, 'Leiblez Z.P.', 3);

INSERT INTO public.films (id, created_by, created_when, deleted_by, deleted_when, is_deleted, country, description, genre, premier_date, title)
VALUES (1, 'SYSTEM', '2014-07-03 23:34:42.000000', NULL, NULL, FALSE, 'TANZANIA', 'calculation pinch never size', 'COMEDY', '2020-06-20', 'Night running'),
       (2, 'SYSTEM', '2020-10-03 23:36:32.000000', NULL, NULL, FALSE, 'UGANDA', 'depend loose foot native', 'ADVENTURE', '2013-06-08', 'Flowers in the rise'),
       (3, 'SYSTEM', '2019-08-05 23:37:40.000000', NULL, NULL, FALSE, 'VIETNAM', 'number composition flour spit', 'DETECTIVE', '2023-05-10', 'Taiga');

INSERT INTO public.role (description, title)
VALUES ('Admin', 'Администратор'),
       ('Manager', 'Менеджер'),
       ('User', 'Пользователь');

INSERT INTO public.users (id,
                          created_by,
                          created_when,
                          deleted_by,
                          deleted_when,
                          is_deleted,
                          address,
                          birth_date,
                          email,
                          first_name,
                          last_name,
                          login,
                          middle_name,
                          password,
                          phone,
                          role_id)
VALUES (1,
        'SYSTEM',
        '2023-07-03 23:39:18.000000',
        NULL,
        NULL,
        FALSE,
        'Savanna, Sunrise 29, r. 20',
        '1998-06-06',
        'savann@mailiz.ru',
        'Dima',
        'Zimenovich',
        'Dima',
        'Borisovich',
        'dima',
        '+79881234568',
        3),
       (2,
        'SYSTEM',
        '2023-07-03 23:42:23.000000',
        NULL,
        NULL,
        FALSE,
        'Novorechensk, Rechnaya 87, r.1'', ''1985-06-12',
        '2015-06-03',
        'parahod@mailiz.ru',
        'Ruslan',
        'Baristov',
        'Ruslan',
        'Yurievich',
        'ruslan',
        '+79771234569',
        3),
       (3,
        'SYSTEM',
        '2023-07-03 23:43:57.000000',
        NULL,
        NULL,
        FALSE,
        'Mnogorechensk, Pobedy 56, r.5',
        '2000-06-10',
        'reka@mailiz.ru',
        'Vasya',
        'Skobar',
        'Vasya',
        'Petrovich',
        'Vasya',
        '+79991234567',
        1),
       (4,
        'SYSTEM',
        '2023-07-03 23:45:23.000000',
        NULL,
        NULL,
        FALSE,
        'asdfasdfasdf',
        '2021-06-02',
        'stealth@stealth.com',
        'stealth',
        'stealth',
        'stealth',
        'stealth',
        '$2a$10$/FAchAsd6gAf1Nz.8rUwduMzOpq7RfhxSdAW0LJNwFSOLeuoD43wm',
        '89150686205',
        3);


INSERT INTO public.orders (id, created_by, created_when, deleted_by, deleted_when, is_deleted, purchase, rent_from, rent_to, user_id)
VALUES (1, 'SYSTEM', '2023-07-03 23:48:08.000000', NULL, NULL, FALSE, TRUE, '2023-06-10', '2023-06-20', 2),
       (2, 'SYSTEM', '2023-07-03 23:49:13.000000', NULL, NULL, FALSE, TRUE, '2023-06-08', '2023-06-30', 1),
       (3, 'SYSTEM', '2023-07-03 23:49:49.000000', NULL, NULL, FALSE, FALSE, '2023-06-18', '2023-06-19', 3),
       (4, 'SYSTEM', '2023-07-03 23:50:21.000000', NULL, NULL, FALSE, TRUE, '2023-06-17', '2023-07-03', 2);

INSERT INTO public.film_directors (film_id, director_id)
VALUES (1, 3),
       (2, 2),
       (3, 1);

INSERT INTO public.order_films (order_id, film_id)
VALUES (1, 3),
       (2, 2),
       (3, 1);