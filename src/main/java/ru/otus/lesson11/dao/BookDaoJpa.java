package ru.otus.lesson11.dao;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.lesson11.model.Author;
import ru.otus.lesson11.model.Book;
import ru.otus.lesson11.model.Genre;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count() {
        TypedQuery<Long> query = em.createQuery("select count(b) from Book b", Long.class);
        return query.getSingleResult();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.author join fetch b.genre", Book.class);
        return query.getResultList();
    }

    @Override
    public List<Book> findAllByAuthor(Author author) {
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.author join fetch b.genre where b.author = :author", Book.class);
        query.setParameter("author", author);
        return query.getResultList();
    }

    @Override
    public List<Book> findAllByGenre(Genre genre) {
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.author join fetch b.genre where b.genre = :genre", Book.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    @Override
    public List<Book> findAllByAuthorAndGenre(Author author, Genre genre) {
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.author join fetch b.genre where b.author = :author and b.genre = :genre", Book.class);
        query.setParameter("author", author);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Book managedEntity = em.find(Book.class, id);
        em.remove(managedEntity);
    }

}
