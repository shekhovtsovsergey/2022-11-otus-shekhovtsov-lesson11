package ru.otus.lesson11.dao;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.lesson11.model.Author;
import ru.otus.lesson11.model.Book;
import ru.otus.lesson11.model.Genre;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertAll;


@DataJpaTest
@Import({BookDaoJpa.class,CommentDaoJpa.class})
@DisplayName("Dao для работы с книгами должно")
class BookDaoJpaTest {
    private static final int EXPECTED_BOOKS_COUNT = 5;
    private static final long BOOK_ID = 1L;
    private static final String BOOK_NAME = "Ned ad trappen, ud på gaden (Danish Edition)";
    private static final long EXPECTED_NEW_ID = 6;
    private static final long AUTHOR_ID = 1L;
    private static final long KING_ID = 2L;
    private static final long COMEDY_ID = 2L;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookDaoJpa bookDaoJpa;
    @Autowired
    private CommentDaoJpa commentDaoJpa;

    @Test
    @DisplayName("возвращать ожидаемое количество книг в БД")
    void count() {
        long count = bookDaoJpa.count();
        assertThat(count).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @Test
    @DisplayName("добавлять книгу в БД")
    void save() {
        Book expectedBook = new Book(null,"Spring in Action",em.find(Author.class, 1L), em.find(Genre.class, 1L),null);
        Book savedBook = bookDaoJpa.save(expectedBook);
        Optional<Book> actualBook = bookDaoJpa.findById(EXPECTED_NEW_ID);
        assertAll(() -> assertThat(savedBook.getId()).isEqualTo(EXPECTED_NEW_ID),
                () -> assertThat(actualBook).isPresent().get()
                        .isEqualTo(expectedBook));
    }

    @Test
    @DisplayName("возвращать ожидаемую книгу по ее id")
    void findById() {
        Optional<Book> actualBook = bookDaoJpa.findById(BOOK_ID);
        assertThat(actualBook).isPresent().get()
                .hasFieldOrPropertyWithValue("id", BOOK_ID)
                .hasFieldOrPropertyWithValue("name", BOOK_NAME);
    }

    @Test
    @DisplayName("возвращать все книги")
    void findAll() {
        List<Book> allBooks = bookDaoJpa.findAll();
        assertThat(allBooks).hasSize(EXPECTED_BOOKS_COUNT);
    }

    @Test
    @DisplayName("возвращать все книги по автору")
    void findAllByAuthor() {
        List<Book> actualBooks = bookDaoJpa.findAllByAuthor(new Author(AUTHOR_ID, null));
        assertAll(() -> assertThat(actualBooks).hasSize(3),
                () -> assertThat(actualBooks.stream()).allMatch(book -> book.getAuthor().getId().equals(AUTHOR_ID)));
    }

    @Test
    @DisplayName("возвращать все книги по жанру")
    void findAllByGenre() {
        List<Book> actualBooks = bookDaoJpa.findAllByGenre(new Genre(COMEDY_ID, null));
        assertAll(() -> assertThat(actualBooks).hasSize(2),
                () -> assertThat(actualBooks.stream()).allMatch(book -> book.getGenre().getId().equals(COMEDY_ID)));
    }

    @Test
    @DisplayName("возвращать все книги по автору и жанру")
    void findAllByAuthorAndGenre() {
        List<Book> actualBooks = bookDaoJpa.findAllByAuthorAndGenre(new Author(KING_ID, null), new Genre(COMEDY_ID, null));
        assertAll(() -> assertThat(actualBooks).hasSize(2),
                () -> assertThat(actualBooks.stream()).allMatch(book -> book.getAuthor().getId().equals(KING_ID)
                        && book.getGenre().getId().equals(COMEDY_ID)));
    }

    @Test
    @DisplayName("обновляет данные книги по id")
    void updateById() {
        bookDaoJpa.updateById(BOOK_ID, "testName", new Author(3L, null), new Genre(2L, null));
        Optional<Book> actualBook = bookDaoJpa.findById(BOOK_ID);
        assertAll(() -> assertThat(actualBook).get().extracting(Book::getName).isEqualTo("testName"),
                () -> assertThat(actualBook).get().extracting(Book::getAuthor).extracting(Author::getId).isEqualTo(3L),
                () -> assertThat(actualBook).get().extracting(Book::getGenre).extracting(Genre::getId).isEqualTo(2L));
    }

    @Test
    @DisplayName("удаляет книгу по id")
    void deleteById() {
        commentDaoJpa.deleteBookById(BOOK_ID);
        bookDaoJpa.deleteById(BOOK_ID);
        List<Book> allBooks = bookDaoJpa.findAll();
        assertAll(() -> assertThat(allBooks).hasSize(EXPECTED_BOOKS_COUNT - 1),
                () -> assertThat(allBooks.stream()).noneMatch(book -> book.getId().equals(BOOK_ID)));
    }
}