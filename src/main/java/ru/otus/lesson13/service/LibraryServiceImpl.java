package ru.otus.lesson13.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lesson13.dao.AuthorDao;
import ru.otus.lesson13.dao.BookDao;
import ru.otus.lesson13.dao.CommentDao;
import ru.otus.lesson13.dao.GenreDao;
import ru.otus.lesson13.model.Author;
import ru.otus.lesson13.model.Book;
import ru.otus.lesson13.model.Comment;
import ru.otus.lesson13.model.Genre;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BookDao bookDao;
    private final CommentDao commentDao;

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAllGenres() {
        return genreDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Long booksCount() {
        return bookDao.count();
    }

    @Override
    @Transactional
    public Book insertBook(Book book) {
        return bookDao.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooksByAuthor(Author author) {
        return bookDao.findAllByAuthor(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooksByGenre(Genre genre) {
        return bookDao.findAllByGenre(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooksByAuthorAndGenre(Author author, Genre genre) {
        return bookDao.findAllByAuthorAndGenre(author, genre);
    }

    @Override
    @Transactional
    public void updateBookById(Long id, String name, Author author, Genre genre) {
        Optional<Book> bookById = bookDao.findById(id);
        bookById.ifPresent(book -> {
            book.setName(name);
            book.setAuthor(author);
            book.setGenre(genre);
            bookDao.save(book);
        });
    }

    @Override
    @Transactional
    public void deleteBookById(Long id) {
        commentDao.deleteCommentByBook(id);
        bookDao.deleteById(id);
    }

    @Override
    @Transactional
    public Comment insertComment(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getCommentById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllCommentsByBook(Book book) {
        return commentDao.findAllByBook(book);
    }

    @Override
    @Transactional
    public void updateCommentById(Long id, String authorName, String comment) {
        commentDao.updateById(id, authorName, comment);
    }

    @Override
    @Transactional
    public void deleteCommentById(Long id) {
        commentDao.deleteById(id);
    }
}
