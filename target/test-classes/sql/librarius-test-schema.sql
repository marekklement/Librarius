CREATE SEQUENCE SEQ_LI_USER INCREMENT BY 20 CACHE 100;
CREATE SEQUENCE SEQ_LI_AUTHOR INCREMENT BY 20 CACHE 100;
CREATE SEQUENCE SEQ_LI_BOOK INCREMENT BY 20 CACHE 100;
CREATE SEQUENCE SEQ_LI_LISTING INCREMENT BY 20 CACHE 100;

create table LI_USER (
  ID_USER           NUMBER(22, 0)      NOT NULL,
  NAME              VARCHAR2(255 CHAR) NOT NULL,
  PASSWORD          VARCHAR2(255 CHAR) NOT NULL,
  SURNAME           VARCHAR2(255 CHAR) NOT NULL,
  USERNAME          VARCHAR2(255 CHAR) NOT NULL,
  REGISTRATION_DATE DATE               NOT NULL,
  LAST_LOGIN_DATE   DATE               NOT NULL,
  CONSTRAINT "PK_LI_USER" PRIMARY KEY (ID_USER)
);

create table LI_BOOK (
  ID_BOOK       NUMBER(22, 0)       NOT NULL,
  LANGUAGE      VARCHAR2(255 CHAR),
  TITLE         VARCHAR2(255 CHAR),
  ISBN          VARCHAR2(255 CHAR),
  BOOK_CATEGORY VARCHAR2(255 CHAR),
  CONSTRAINT "PK_LI_BOOK" PRIMARY KEY (ID_BOOK)
);

create table LI_BOOK_ISBN (
  ID_ISBN   NUMBER(22, 0)   NOT NULL,
  ISBNS     NUMBER(22, 0)   NOT NULL,
  CONSTRAINT "PK_LI_BOOK_ISBN" PRIMARY KEY (ID_ISBN)
);

create table LI_BOOK_CATEGORY (
  ID_BOOK_CATEGORY NUMBER (22, 0) NOT NULL,
  BOOKCATEGORIES    NUMBER (22, 0) NOT NULL,
  CONSTRAINT "PK_LI_BOOK_CATEGORY" PRIMARY KEY (ID_BOOK_CATEGORY)
);

create table LI_AUTHOR (
  ID_AUTHOR NUMBER(22, 0)      not null,
  BIRTH_DATE DATE,
  COUNTRY   VARCHAR2(255 CHAR),
  NAME      VARCHAR2(255 CHAR) NOT NULL,
  CONSTRAINT "PK_LI_AUTHOR" PRIMARY KEY (ID_AUTHOR)
);

create table LI_BOOK_AUTH (
  ID_BOOK   NUMBER (22,0) NOT NULL,
  ID_AUTHOR NUMBER (22,0) NOT NULL,
  CONSTRAINT "PK_LI_BOOK_AUTH" PRIMARY KEY (ID_AUTHOR, ID_BOOK),
  CONSTRAINT "FK_BA_BOOK" FOREIGN KEY (ID_BOOK) REFERENCES "LI_BOOK" (ID_BOOK),
  CONSTRAINT "FK_BA_AUTHOR" FOREIGN KEY (ID_AUTHOR) REFERENCES LI_AUTHOR (ID_AUTHOR)
);

create table LI_LISTING (
  ID_LISTING    NUMBER(22, 0) NOT NULL,
  CREATION_TIME TIMESTAMP,
  AUTO_GRAPHED  NUMBER(1,0),
  ISBN          NUMBER(22, 0),
  PRICE         NUMBER(22, 4),
  ID_BOOK       NUMBER(22, 0),
  ID_USER       NUMBER(22, 0),
  CONSTRAINT "PK_LI_LISTING" PRIMARY KEY (ID_LISTING),
  CONSTRAINT "FK_BOOK_LISTING" FOREIGN KEY (ID_BOOK) REFERENCES LI_BOOK(ID_BOOK)
);