package ru.otus.lesson13.dao;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson13.model.Author;
import ru.otus.lesson13.model.Book;
import ru.otus.lesson13.model.Genre;

import java.util.List;


public interface BookDao extends JpaRepository<Book, Long> {

    @EntityGraph("Book.allAttributes")
    List<Book> findAll();

    @EntityGraph("Book.allAttributes")
    List<Book> findAllByAuthor(Author author);

    @EntityGraph("Book.allAttributes")
    List<Book> findAllByGenre(Genre genre);

    @EntityGraph("Book.allAttributes")
    List<Book> findAllByAuthorAndGenre(Author author, Genre genre);


}