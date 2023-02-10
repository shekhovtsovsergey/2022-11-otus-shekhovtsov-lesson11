package ru.otus.lesson11.service;


import lombok.RequiredArgsConstructor;
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
    public String getAllAuthors() {
        List<Author> authors = authorDao.findAll();
        return String.format("Все авторы библиотеки: %s", authors);
    }

    @Override
    public String getAllGenres() {
        List<Genre> genres = genreDao.findAll();
        return String.format("Все жанры библиотеки: %s", genres);
    }

    @Override
    public Long booksCount() {
        return bookDao.count();
    }

    @Override
    @Transactional
    public String insertBook(String bookName,Long authorId,Long genreId) {
        bookDao.save((new Book(null, bookName, new Author(authorId, null), new Genre(genreId, null))));
        return String.format("Книга добавлена:");
    }

    @Override
    @Transactional
    public String updateBook(Long id, String bookName,Long authorId,Long genreId) {
        bookDao.save((new Book(id, bookName, new Author(authorId, null), new Genre(genreId, null))));
        return String.format("Книга обновлена:");
    }

    @Override
    @Transactional
    public String getBookById(Long id) {
        Optional<Book> book =bookDao.findById(id);
        return book.isEmpty() ?  String.format("Книги с id: %d не существует", id) : String.format("Вы взяли книгу: %s", book);
    }

    @Override
    @Transactional
    public String getAllBooks() {
        List<Book> allBooks = bookDao.findAll();
        return String.format("Все книги библиотеки: %s", allBooks);
    }

    @Override
    @Transactional
    public String getAllBooksByAuthor(Long authorId) {
        List<Book> allBooksByAuthor = bookDao.findAllByAuthor(new Author(authorId, null));
        return String.format("Вы взяли следующие книги по автору: %s", allBooksByAuthor);
    }

    @Override
    @Transactional
    public String getAllBooksByGenre(Long genreId) {
        List<Book> allBooksByGenre = bookDao.findAllByGenre(new Genre(genreId, null));
        return String.format("Вы взяли следующие книги по жанру: %s", allBooksByGenre);
    }

    @Override
    @Transactional
    public String getAllBooksByAuthorAndGenre(Long authorId, Long genreId) {
        List<Book> allBooksByAuthorAndGenre = bookDao.findAllByAuthorAndGenre(
                new Author(authorId, null),
                new Genre(genreId, null));
        return String.format("Вы взяли следующие книги по автору и жанру: %s", allBooksByAuthorAndGenre);
    }

    @Override
    @Transactional
    public String deleteBookById(Long id) {
        commentDao.deleteBookById(id);
        bookDao.deleteById(id);
        return String.format("Книга удалена");
    }

    @Override
    @Transactional
    public String insertComment(Long bookId,  String authorName,  String comment) {
        commentDao.save(new Comment(null, new Book(bookId, null, null, null,null), authorName, comment));
        return String.format("Комментарий добавлен");
    }

    @Override
    @Transactional
    public String updateComment(Long id, Long bookId,  String authorName,  String comment) {
        commentDao.save(new Comment(id, new Book(bookId, null, null, null,null), authorName, comment));
        return String.format("Комментарий изменен");
    }

    @Override
    public String getCommentById(Long id) {
        Optional<Comment> comments = commentDao.findById(id);
        return String.format("Комментарий: %s", comments);
    }

    @Override
    @Transactional
    public String getAllCommentsByBook(Long bookId) {
        List<Comment> allCommentsByBook = bookDao.findById(bookId).get().getComments();
        return String.format("Вы взяли следующие комментарии по книге: %s", allCommentsByBook);
    }

    @Override
    @Transactional
    public String deleteCommentById(Long id) {
        commentDao.deleteById(id);
        return String.format("Комментарий удален");
    }
}
