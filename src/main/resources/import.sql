INSERT INTO LI_AUTHOR("id_author", "country", "name") VALUES
   (1000, 'CZ', 'Nicolas'),
   (2000, 'SK', 'Nicolas');

INSERT INTO LI_USER("username", "password", "name", "surname", "registration_date", "last_login_date") VALUES
   ('user', 'password', 'Nicolas', 'Cage', to_date('1963-09-01', 'YYYY-MM-DD'), to_date('1963-09-01', 'YYYY-MM-DD'));

INSERT INTO LI_BOOK("id_book", "language", "title") VALUES
   (1000, 'CZ', 'JajaAPaja'),
   (2000, 'SK', 'KrtecekAKalhotky');

INSERT INTO LI_BOOK_AUTH("id_book","id_author") VALUES
    (1000, 1000),
    (2000, 2000);

INSERT INTO LI_LISTING("id_listing", "isbn", "price", "creation_time", "auto_graphed", "id_book", "id_user", "invalidated") VALUES
   (1000, 9788020009807, 100, to_date('1963-09-01', 'YYYY-MM-DD'), false, 1000, 'user', false ),
   (2000, 9788020009808, 99, to_date('1963-09-01', 'YYYY-MM-DD'), false, 2000, 'user', false );

