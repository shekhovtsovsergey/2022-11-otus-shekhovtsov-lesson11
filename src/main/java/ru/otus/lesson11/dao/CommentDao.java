package ru.otus.lesson11.dao;


import ru.otus.lesson11.model.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentDao {

    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    void deleteById(Long id);
    void deleteBookById(Long id);
    List<Comment> findAll();

}
