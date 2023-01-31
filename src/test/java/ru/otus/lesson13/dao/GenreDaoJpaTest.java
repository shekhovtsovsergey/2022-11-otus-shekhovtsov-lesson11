package ru.otus.lesson13.dao;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.lesson13.model.Genre;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Dao для работы с жанрами должно")
class GenreDaoJpaTest {
    private static final long GENRE_ID = 1;
    private static final String GENRE_NAME = "Documental";
    private static final int EXPECTED_GENRES_COUNT = 2;

    @Autowired
    private GenreDao genreDao;

    @Test
    @DisplayName("возвращать ожидаемый жанр по его id")
    void findById() {
        Optional<Genre> actualGenre = genreDao.findById(GENRE_ID);
        assertThat(actualGenre).isPresent().get()
                .hasFieldOrPropertyWithValue("id", GENRE_ID)
                .hasFieldOrPropertyWithValue("name", GENRE_NAME);
    }

    @Test
    @DisplayName("возвращать все жанры")
    void findAll() {
        List<Genre> allGenres = genreDao.findAll();
        assertThat(allGenres).hasSize(EXPECTED_GENRES_COUNT);
    }
}