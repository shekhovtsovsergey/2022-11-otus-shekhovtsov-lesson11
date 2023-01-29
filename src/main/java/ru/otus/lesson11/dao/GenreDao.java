package ru.otus.lesson11.dao;


import ru.otus.lesson11.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    Optional<Genre> findById(Long id);

    List<Genre> findAll();

}
