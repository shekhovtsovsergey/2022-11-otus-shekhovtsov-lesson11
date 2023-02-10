package ru.otus.lesson11.dao;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.lesson11.model.Book;
import ru.otus.lesson11.model.Comment;
import javax.persistence.*;
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
    public void deleteById(Long id) {
        Comment managedEntity = em.find(Comment.class, id);
        em.remove(managedEntity);
    }

    @Override
    public void deleteBookById(Long id) {
        List<Comment> managedEntity = em.find(Book.class, id).getComments();
        for (Comment element : managedEntity) {
            em.remove(element);
        }
    }

    @Override
    public List<Comment> findAll() {
        Query query = em.createQuery("select c from Comment c");
        return query.getResultList();
    }


}
