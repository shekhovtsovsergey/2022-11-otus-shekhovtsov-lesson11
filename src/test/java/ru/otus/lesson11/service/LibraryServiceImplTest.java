package ru.otus.lesson11.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.lesson11.dao.AuthorDao;
import ru.otus.lesson11.dao.BookDao;
import ru.otus.lesson11.dao.CommentDao;
import ru.otus.lesson11.dao.GenreDao;
import ru.otus.lesson11.model.Author;
import ru.otus.lesson11.model.Book;
import ru.otus.lesson11.model.Comment;
import ru.otus.lesson11.model.Genre;
import static org.mockito.Mockito.verify;



@SpringBootTest
@DisplayName("Сервис библиотеки должен")
class LibraryServiceImplTest {

    @Configuration
    @Import(LibraryServiceImpl.class)
    static class NestedConfiguration {
    }

    @MockBean
    private AuthorDao authorDao;
    @MockBean
    private GenreDao genreDao;
    @MockBean
    private BookDao bookDao;
    @MockBean
    private CommentDao commentDao;

    @Autowired
    private LibraryService libraryService;

    @Test
    @DisplayName("корректно вызывать authorDao")
    void getAllAuthors() {
        libraryService.getAllAuthors();
        verify(authorDao).findAll();
    }

    @Test
    @DisplayName("корректно вызывать genreDao")
    void getAllGenres() {
        libraryService.getAllGenres();
        verify(genreDao).findAll();
    }

    @Test
    @DisplayName("корректно вызывать bookDao.count")
    void booksCount() {
        libraryService.booksCount();
        verify(bookDao).count();
    }

    @Test
    @DisplayName("корректно вызывать bookDao.save")
    void insertBook() {
        Book book = new Book(null, null, null, null,null);
        libraryService.insertBook(book);
        verify(bookDao).save(book);
    }

    @Test
    @DisplayName("корректно вызывать bookDao.findById")
    void getBookById() {
        libraryService.getBookById(1L);
        verify(bookDao).findById(1L);
    }

    @Test
    @DisplayName("корректно вызывать bookDao.findAll")
    void getAllBooks() {
        libraryService.getAllBooks();
        verify(bookDao).findAll();
    }

    @Test
    @DisplayName("корректно вызывать bookDao.findAllByAuthor")
    void getAllBooksByAuthor() {
        Author author = new Author(null, null);
        libraryService.getAllBooksByAuthor(author);
        verify(bookDao).findAllByAuthor(author);
    }

    @Test
    @DisplayName("корректно вызывать bookDao.findAllByGenre")
    void getAllBooksByGenre() {
        Genre genre = new Genre(null, null);
        libraryService.getAllBooksByGenre(genre);
        verify(bookDao).findAllByGenre(genre);
    }

    @Test
    @DisplayName("корректно вызывать bookDao.findAllByAuthorAndGenre")
    void getAllBooksByAuthorAndGenre() {
        Author author = new Author(null, null);
        Genre genre = new Genre(null, null);
        libraryService.getAllBooksByAuthorAndGenre(author, genre);
        verify(bookDao).findAllByAuthorAndGenre(author, genre);
    }

    @Test
    @DisplayName("корректно вызывать bookDao.updateById")
    void updateBookById() {
        Author author = new Author(2L, null);
        Genre genre = new Genre(3L, null);
        libraryService.insertBook(new Book(1L, "newName", author, genre));
        verify(bookDao).save(new Book(1L, "newName", author, genre));
    }

    @Test
    @DisplayName("корректно вызывать bookDao.deleteById")
    void deleteBooksById() {
        libraryService.deleteBookById(1L);
        verify(bookDao).deleteById(1L);
    }

    @Test
    @DisplayName("корректно вызывать commentDao.save")
    public void insertComment() {
        Comment comment = new Comment(1L, null, null, null);
        libraryService.insertComment(comment);
        verify(commentDao).save(comment);
    }

    @Test
    @DisplayName("корректно вызывать commentDao.findById")
    public void getCommentById() {
        libraryService.getCommentById(1L);
        verify(commentDao).findById(1L);
    }

    @Test
    @DisplayName("корректно вызывать commentDao.deleteById")
    public void deleteCommentById() {
        libraryService.deleteCommentById(1L);
        verify(commentDao).deleteById(1L);
    }
}