package ru.otus.lesson11.dao;


import ru.otus.lesson11.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    Optional<Author> findById(Long id);

    List<Author> findAll();

}
