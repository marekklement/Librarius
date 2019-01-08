INSERT INTO LI_AUTHOR("id_author", "country", "name") VALUES
   (1, 'CZ', 'Nicolas'),
   (2, 'SK', 'Nicolas');

INSERT INTO LI_USER("username", "password", "name", "surname", "registration_date", "last_login_date") VALUES
   ('user', 'pass', 'Nicolas', 'Cage', to_date('1963-09-01', 'YYYY-MM-DD'), to_date('1963-09-01', 'YYYY-MM-DD'));

INSERT INTO LI_BOOK("id_book", "language", "title") VALUES
   (1, 'CZ', 'JajaAPaja'),
   (2, 'SK', 'KrtecekAKalhotky');

INSERT INTO LI_LISTING("id_listing", "isbn", "price", "creation_time", "auto_graphed", "id_book") VALUES
   (1, 1234, 100, to_date('1963-09-01', 'YYYY-MM-DD'), false, 1),
   (2, 9876, 99, to_date('1963-09-01', 'YYYY-MM-DD'), false, 2);

