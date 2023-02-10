package ru.otus.lesson11.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lesson11.service.LibraryService;


@ShellComponent
@RequiredArgsConstructor
public class LibraryCommands {
    private final LibraryService libraryService;

    @ShellMethod(value = "Get all authors command", key = {"authors"})
    public String authors() {
        return libraryService.getAllAuthors();
    }

    @ShellMethod(value = "Get all genres command", key = {"genres"})
    public String genres() {
        return libraryService.getAllGenres();
    }

    @ShellMethod(value = "Get books count", key = {"booksCount"})
    public Long count() {
        return libraryService.booksCount();
    }

    @ShellMethod(value = "Insert book command", key = {"insertBook"})
    public String insertBook(@ShellOption String bookName, @ShellOption Long authorId, @ShellOption Long genreId) {
        return libraryService.insertBook(bookName,authorId,genreId);
    }

    @ShellMethod(value = "Get books by id", key = {"bookById"})
    public String getBookById(@ShellOption Long bookId) {
        return libraryService.getBookById(bookId);
    }

    @ShellMethod(value = "Get all books", key = {"allBooks"})
    public String getAllBooks() {
        return libraryService.getAllBooks();
    }

    @ShellMethod(value = "Get books by author id", key = {"booksByAuthorId"})
    public String getAllBooksByAuthor(@ShellOption Long authorId) {
        return libraryService.getAllBooksByAuthor(authorId);
    }

    @ShellMethod(value = "Get books by genre id", key = {"booksByGenreId"})
    public String getAllBooksByGenre(@ShellOption Long genreId) {
        return libraryService.getAllBooksByGenre(genreId);
    }

    @ShellMethod(value = "Get books by author id and genre id", key = {"booksByAuthorIdAndGenreId"})
    public String getAllBooksByAuthorAndGenre(@ShellOption Long authorId, @ShellOption Long genreId) {
        return libraryService.getAllBooksByAuthorAndGenre(authorId,genreId);
    }

    @ShellMethod(value = "Update book by id", key = {"updateBook"})
    public String updateBookById(@ShellOption Long id, @ShellOption String bookName, @ShellOption Long authorId, @ShellOption Long genreId) {
        return libraryService.updateBook(id, bookName, authorId, genreId);
    }

    @ShellMethod(value = "Delete book by id", key = {"deleteBookById"})
    public String deleteBooksById(@ShellOption Long bookId) {
        return libraryService.deleteBookById(bookId);
    }

    @ShellMethod(value = "Get comments by id", key = {"getCommentById"})
    public String getCommentById(@ShellOption Long id) {
        return libraryService.getCommentById(id);
    }

    @ShellMethod(value = "Insert comment command", key = {"insertComment"})
    public String insertComment(@ShellOption Long bookId, @ShellOption String authorName, @ShellOption String comment) {
        return libraryService.insertComment(bookId, authorName, comment);
    }

    @ShellMethod(value = "Get comments by book id", key = {"commentsByBookId"})
    public String getAllCommentsByBookId(@ShellOption Long bookId) {
        return libraryService.getAllCommentsByBook(bookId);
    }

    @ShellMethod(value = "Update comment by id", key = {"updateComment"})
    public String updateCommentById(@ShellOption Long id, @ShellOption Long bookId, @ShellOption String authorName, @ShellOption String comment) {
        return libraryService.updateComment(id,  bookId,  authorName,  comment);
    }

    @ShellMethod(value = "Delete comment by id", key = {"deleteCommentById"})
    public String deleteCommentById(@ShellOption Long commentId) {
        return libraryService.deleteCommentById(commentId);
    }

}
