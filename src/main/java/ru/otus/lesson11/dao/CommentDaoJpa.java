package ru.otus.lesson11.dao;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.lesson11.model.Book;
import ru.otus.lesson11.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAllByBook(Book book) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book = :book", Comment.class);
        query.setParameter("book", book);
        return query.getResultList();
    }

    @Override
    public void updateById(Long id, String authorName, String comment) {
        Query query = em.createQuery("update Comment c set c.authorName = :authorName, c.comment = :comment where c.id = :id");
        query.setParameter("id", id);
        query.setParameter("authorName", authorName);
        query.setParameter("comment", comment);
        query.executeUpdate();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
