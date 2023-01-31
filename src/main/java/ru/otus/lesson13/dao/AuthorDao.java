package ru.otus.lesson13.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson13.model.Author;


public interface AuthorDao extends JpaRepository<Author, Long> {

}
