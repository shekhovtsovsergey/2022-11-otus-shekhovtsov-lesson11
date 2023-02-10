package ru.otus.lesson11.service;


public interface LibraryService {

    String getAllAuthors();
    String getAllGenres();
    Long booksCount();
    String insertBook(String bookName,Long authorId,Long genreId);
    String updateBook(Long id, String bookName,Long authorId,Long genreId);
    String getBookById(Long id);
    String getAllBooks();
    String getAllBooksByAuthor(Long authorId);
    String getAllBooksByGenre(Long genreId);
    String getAllBooksByAuthorAndGenre(Long authorId, Long genreId);
    String deleteBookById(Long id);
    String insertComment(Long bookId,  String authorName,  String comment);
    String updateComment(Long id, Long bookId,  String authorName,  String comment);
    String getCommentById(Long id);
    String getAllCommentsByBook(Long bookId);
    String deleteCommentById(Long id);

}
