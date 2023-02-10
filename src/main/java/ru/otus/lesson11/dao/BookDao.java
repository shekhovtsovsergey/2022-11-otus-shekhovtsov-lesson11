package ru.otus.lesson11.dao;


import ru.otus.lesson11.model.Author;
import ru.otus.lesson11.model.Book;
import ru.otus.lesson11.model.Genre;
import java.util.List;
import java.util.Optional;


public interface BookDao {

    Long count();
    Book save(Book book);
    Optional<Book> findById(Long id);
    List<Book> findAll();
    List<Book> findAllByAuthor(Author author);
    List<Book> findAllByGenre(Genre genre);
    List<Book> findAllByAuthorAndGenre(Author author, Genre genre);
    void deleteById(Long id);

}
