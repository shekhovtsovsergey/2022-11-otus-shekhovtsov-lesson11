package ru.otus.lesson11.service;


import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lesson11.dao.AuthorDao;
import ru.otus.lesson11.dao.BookDao;
import ru.otus.lesson11.dao.CommentDao;
import ru.otus.lesson11.dao.GenreDao;
import ru.otus.lesson11.model.Author;
import ru.otus.lesson11.model.Book;
import ru.otus.lesson11.model.Comment;
import ru.otus.lesson11.model.Genre;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BookDao bookDao;
    private final CommentDao commentDao;

    @Override
    public List<Author> getAllAuthors() {
        return authorDao.findAll();
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreDao.findAll();
    }

    @Override
    public Long booksCount() {
        return bookDao.count();
    }

    @Override
    @Transactional
    public Book insertBook(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> getAllBooksByAuthor(Author author) {
        return bookDao.findAllByAuthor(author);
    }

    @Override
    public List<Book> getAllBooksByGenre(Genre genre) {
        return bookDao.findAllByGenre(genre);
    }

    @Override
    public List<Book> getAllBooksByAuthorAndGenre(Author author, Genre genre) {
        return bookDao.findAllByAuthorAndGenre(author, genre);
    }

    @Override
    @Transactional
    public void deleteBookById(Long id) {
        commentDao.deleteBookById(id);
        bookDao.deleteById(id);
    }

    @Override
    @Transactional
    public Comment insertComment(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    public List<Comment> getAllCommentsByBook(Book book) {
        return bookDao.findById(book.getId()).get().getComments();
    }

    @Override
    public void deleteCommentById(Long id) {
        commentDao.deleteById(id);
    }
}
