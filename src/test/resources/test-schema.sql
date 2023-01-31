drop table if exists BOOK;
drop table if exists GENRE;
drop table if exists AUTHOR;
drop table if exists COMMENTS;

create table author(id bigint not null auto_increment,
                    name varchar(255) not null,
                    primary key (id));

create table genre(id bigint not null auto_increment,
                    name varchar(255) not null,
                    primary key (id));

create table book(id bigint not null auto_increment,
                  name varchar(255) not null,
                  author_id bigint not null,
                  genre_id bigint not null,
                  primary key (id),
                  foreign key (author_id) references author(id),
                  foreign key (genre_id) references genre(id));

create table comments(id bigint not null auto_increment,
                      book_id bigint not null,
                      author_name varchar(255) not null default 'incognita',
                      comment text not null,
                      primary key (id),
                      foreign key (book_id) references books(id) on delete cascade);
