package ru.otus.lesson11.dao;


import ru.otus.lesson11.model.Book;
import ru.otus.lesson11.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    Comment save(Comment comment);

    Optional<Comment> findById(Long id);

    List<Comment> findAllByBook(Book book);

    void updateById(Long id, String authorName, String comment);

    void deleteById(Long id);

    void deleteBookById(Long id);

}
