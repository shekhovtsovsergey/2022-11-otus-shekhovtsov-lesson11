package ru.otus.lesson13.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.lesson13.model.Book;
import ru.otus.lesson13.model.Comment;
import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBook(Book book);

    @Modifying
    @Query("update Comment c set c.authorName = :authorName, c.comment = :comment where c.id = :id")
    void updateById(@Param("id") Long id, @Param("authorName") String authorName, @Param("comment") String comment);

    @Modifying
    @Query("delete from Comment c where c.book.id = :id")
    void deleteCommentByBook(@Param("id") Long id);


}
