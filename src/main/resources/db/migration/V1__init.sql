drop table if exists book;
drop table if exists genre;
drop table if exists author;

CREATE TABLE AUTHOR
(
    ID             BIGSERIAL        PRIMARY KEY,
    NAME           VARCHAR(255)     NOT NULL
);


CREATE TABLE GENRE
(
    ID             BIGSERIAL        PRIMARY KEY,
    NAME           VARCHAR(255)     NOT NULL
);

CREATE TABLE BOOK
(
    ID             BIGSERIAL        PRIMARY KEY,
    NAME           VARCHAR(255)     NOT NULL,
    AUTHOR_ID      BIGINT           NOT NULL,
    GENRE_ID       BIGINT           NOT NULL,

    CONSTRAINT fk_author
        FOREIGN KEY (AUTHOR_ID)
            REFERENCES AUTHOR (ID),

    CONSTRAINT fk_GENRE
        FOREIGN KEY (GENRE_ID)
            REFERENCES GENRE (ID)
);


INSERT INTO AUTHOR (NAME) VALUES('Lean Nielsen');
INSERT INTO AUTHOR (NAME) VALUES('Klaus Rifbjerg');
INSERT INTO AUTHOR (NAME) VALUES('Thorkild Bjørnvig');
INSERT INTO AUTHOR (NAME) VALUES('Cecil Bødker');
INSERT INTO AUTHOR (NAME) VALUES('Grete Stenbæk');

insert into GENRE (name) values('Documental');
insert into GENRE (name) values('History');

INSERT INTO BOOK (NAME, AUTHOR_ID,GENRE_ID) VALUES ('Ned ad trappen, ud på gaden (Danish Edition)',1,1);
INSERT INTO BOOK (NAME, AUTHOR_ID,GENRE_ID) VALUES ('Kesses krig (Unge læsere) (Danish Edition)',1,1);
INSERT INTO BOOK (NAME, AUTHOR_ID,GENRE_ID) VALUES ('Hjørnestuen og månehavet: Erindringer 1934-1938 (Danish Edition)',2,2);
INSERT INTO BOOK (NAME, AUTHOR_ID,GENRE_ID) VALUES ('Vandgården: Roman (Danish Edition)',2,2);
INSERT INTO BOOK (NAME, AUTHOR_ID,GENRE_ID) VALUES ('Thea (Danish Edition)',1,1);



