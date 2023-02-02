package ru.otus.lesson11.dao;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.lesson11.model.Book;
import ru.otus.lesson11.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
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
    public void deleteById(Long id) {
        Comment managedEntity = em.find(Comment.class, id);
        em.remove(managedEntity);
    }

    @Override
    public void deleteBookById(Long id) {
        Query query = em.createQuery("delete from Comment c where c.book.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Comment> findAll() {
        Query query = em.createQuery("select c from Comment c");
        return query.getResultList();
    }


}
