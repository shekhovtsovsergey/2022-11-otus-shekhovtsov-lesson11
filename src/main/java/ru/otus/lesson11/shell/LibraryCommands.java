package ru.otus.lesson11.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lesson11.model.Author;
import ru.otus.lesson11.model.Book;
import ru.otus.lesson11.model.Comment;
import ru.otus.lesson11.model.Genre;
import ru.otus.lesson11.service.LibraryService;

import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class LibraryCommands {
    private final LibraryService libraryService;

    @ShellMethod(value = "Get all authors command", key = {"authors"})
    public String authors() {
        List<Author> authors = libraryService.getAllAuthors();
        return String.format("Все авторы библиотеки: %s", authors);
    }

    @ShellMethod(value = "Get all genres command", key = {"genres"})
    public String genres() {
        List<Genre> genres = libraryService.getAllGenres();
        return String.format("Все жанры библиотеки: %s", genres);
    }

    @ShellMethod(value = "Get books count", key = {"booksCount"})
    public long count() {
        return libraryService.booksCount();
    }

    @ShellMethod(value = "Insert book command", key = {"insertBook"})
    public String insertBook(@ShellOption String bookName, @ShellOption Long authorId, @ShellOption Long genreId) {
        libraryService.insertBook(new Book(null, bookName, new Author(authorId, null), new Genre(genreId, null)));
        return String.format("Книга добавлена:");
    }

    @ShellMethod(value = "Get books by id", key = {"bookById"})
    public String getBookById(@ShellOption Long bookId) {
        Optional<Book> book = libraryService.getBookById(bookId);
        return book.isPresent() ? String.format("Вы взяли книгу: %s", book.get()) : String.format("Книги с id: %d не существует", bookId);
    }

    @ShellMethod(value = "Get all books", key = {"allBooks"})
    public String getAllBooks() {
        List<Book> allBooks = libraryService.getAllBooks();
        return String.format("Все книги библиотеки: %s", allBooks);
    }

    @ShellMethod(value = "Get books by author id", key = {"booksByAuthorId"})
    public String getAllBooksByAuthor(@ShellOption Long authorId) {
        List<Book> allBooksByAuthor = libraryService.getAllBooksByAuthor(new Author(authorId, null));
        return String.format("Вы взяли следующие книги по автору: %s", allBooksByAuthor);
    }

    @ShellMethod(value = "Get books by genre id", key = {"booksByGenreId"})
    public String getAllBooksByGenre(@ShellOption Long genreId) {
        List<Book> allBooksByGenre = libraryService.getAllBooksByGenre(new Genre(genreId, null));
        return String.format("Вы взяли следующие книги по жанру: %s", allBooksByGenre);
    }

    @ShellMethod(value = "Get books by author id and genre id", key = {"booksByAuthorIdAndGenreId"})
    public String getAllBooksByAuthorAndGenre(@ShellOption Long authorId, @ShellOption Long genreId) {
        List<Book> allBooksByAuthorAndGenre = libraryService.getAllBooksByAuthorAndGenre(
                new Author(authorId, null),
                new Genre(genreId, null));
        return String.format("Вы взяли следующие книги по автору и жанру: %s", allBooksByAuthorAndGenre);
    }

    @ShellMethod(value = "Update book by id", key = {"updateBook"})
    public String updateBookById(@ShellOption Long id, @ShellOption String bookName, @ShellOption Long authorId, @ShellOption Long genreId) {
        libraryService.insertBook(new Book(id, bookName, new Author(authorId, null), new Genre(genreId, null)));
        return String.format("Книга обновлена:");
    }

    @ShellMethod(value = "Delete book by id", key = {"deleteBookById"})
    public String deleteBooksById(@ShellOption Long bookId) {
        libraryService.deleteBookById(bookId);
        return String.format("Книга удалена");
    }

    @ShellMethod(value = "Insert comment command", key = {"insertComment"})
    public String insertComment(@ShellOption Long bookId, @ShellOption String authorName, @ShellOption String comment) {
        libraryService.insertComment(new Comment(null, new Book(bookId, null, null, null,null), authorName, comment));
        return String.format("Комментарий добавлен");
    }

    @ShellMethod(value = "Get comments by book id", key = {"commentsByBookId"})
    public String getAllCommentsByBookId(@ShellOption Long bookId) {
        List<Comment> allCommentsByBook = libraryService.getAllCommentsByBook(new Book(bookId, null, null, null,null));
        return String.format("Вы взяли следующие комментарии по книге: %s", allCommentsByBook);
    }

    @ShellMethod(value = "Update comment by id", key = {"updateComment"})
    public String updateCommentById(@ShellOption Long id, @ShellOption Long bookId, @ShellOption String authorName, @ShellOption String comment) {
        libraryService.insertComment(new Comment(id,new Book(bookId, null, null, null,null), authorName, comment));
        return String.format("Комментарий изменен");
    }

    @ShellMethod(value = "Delete comment by id", key = {"deleteCommentById"})
    public String deleteCommentById(@ShellOption Long commentId) {
        libraryService.deleteCommentById(commentId);
        return String.format("Комментарий удален");
    }

}
