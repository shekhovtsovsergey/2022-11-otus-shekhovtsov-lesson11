insert into author (id, name) values (1, 'Lean Nielsen'),
                                     (2, 'Klaus Rifbjerg'),
                                     (3, 'Thorkild Bjørnvig');

insert into genre (id, name) values (1, 'Comedy'),
                                    (2, 'Comedy'),
                                    (3, 'Comedy'),
                                    (4, 'Comedy');

insert into book (id, name, author_id, genre_id) values (1, 'Ned ad trappen, ud på gaden (Danish Edition)', 2, 4),
                                                        (2, 'Ned ad trappen, ud på gaden (Danish Edition)', 2, 4),
                                                        (3, 'Ned ad trappen, ud på gaden (Danish Edition)', 1, 1),
                                                        (4, 'Ned ad trappen, ud på gaden (Danish Edition)', 3, 2),
                                                        (5, 'Ned ad trappen, ud på gaden (Danish Edition)', 3, 3);


insert into comments (id, book_id, author_name, comment) values (1, 1, 'Петр 1', 'Ничего не понял, но очень интересно'),
                                                                (2, 1, 'Местный хипстер Никита', 'Ничего не понял, пойду лучше выпью смузи и сразу в барбишоп'),
                                                                (3, 1, 'Студент Серега', 'Отличная увлекательная книга'),
                                                                (4, 2, 'Петр 1', 'Ничего не понял, но очень интересно'),
                                                                (5, 2, 'Студент Санек', 'Прочитал взахлеб, посоветую соседу в общаге Сереге'),
                                                                (6, 3, 'Петр 1', 'Книга очень интересная, жаль мало текста'),
                                                                (7, 4, 'Петр 1', 'Прекрасно! Няни читают наследникам'),
                                                                (8, 5, 'Вася школьник', 'Задали в школе, спасибо, еле нашел');