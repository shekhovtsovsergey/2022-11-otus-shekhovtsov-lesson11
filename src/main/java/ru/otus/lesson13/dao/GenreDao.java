package ru.otus.lesson13.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson13.model.Genre;



public interface GenreDao extends JpaRepository<Genre, Long> {

}