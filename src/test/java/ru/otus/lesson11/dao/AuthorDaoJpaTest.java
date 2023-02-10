package ru.otus.lesson11.dao;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.lesson11.model.Author;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@Import(AuthorDaoJpa.class)
@DisplayName("Dao для работы с авторами должно")
class AuthorDaoJpaTest {
    private static final long TEST_ID = 2L;
    private static final String TEST_NAME = "Klaus Rifbjerg";
    private static final int EXPECTED_AUTHORS_COUNT = 5;

    @Autowired
    private AuthorDaoJpa authorDaoJpa;

    @Test
    @DisplayName("возвращать ожидаемого автора по его id")
    void findById() {
        Optional<Author> actualAuthor = authorDaoJpa.findById(TEST_ID);
        assertThat(actualAuthor).isPresent().get()
                .hasFieldOrPropertyWithValue("id", TEST_ID)
                .hasFieldOrPropertyWithValue("name", TEST_NAME);
    }



    @Test
    @DisplayName("возвращать всех авторов")
    void findAll() {
        List<Author> allAuthors = authorDaoJpa.findAll();
        assertThat(allAuthors).hasSize(EXPECTED_AUTHORS_COUNT);
    }

}