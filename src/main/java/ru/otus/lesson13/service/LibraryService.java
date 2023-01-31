package ru.otus.lesson13.service;


import ru.otus.lesson13.model.Author;
import ru.otus.lesson13.model.Book;
import ru.otus.lesson13.model.Comment;
import ru.otus.lesson13.model.Genre;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    List<Author> getAllAuthors();

    List<Genre> getAllGenres();

    Long booksCount();

    Book insertBook(Book book);

    Optional<Book> getBookById(Long id);

    List<Book> getAllBooks();

    List<Book> getAllBooksByAuthor(Author author);

    List<Book> getAllBooksByGenre(Genre genre);

    List<Book> getAllBooksByAuthorAndGenre(Author author, Genre genre);

    void updateBookById(Long id, String name, Author author, Genre genre);

    void deleteBookById(Long id);

    Comment insertComment(Comment comment);

    Optional<Comment> getCommentById(Long id);

    List<Comment> getAllCommentsByBook(Book book);

    void updateCommentById(Long id, String authorName, String comment);

    void deleteCommentById(Long id);

}
