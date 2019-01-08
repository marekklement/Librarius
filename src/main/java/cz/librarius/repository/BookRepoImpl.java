package cz.librarius.repository;

import cz.librarius.domain.Book;

import javax.persistence.TypedQuery;
import java.util.List;

public class BookRepoImpl extends GenericRepository<Book> implements BookRepository {
    @Override
    public List<Book> findAllByTitle(String title) {
        TypedQuery<Book> query = em
                .createNamedQuery("findBookByTitle", Book.class)
                .setParameter("title", title);
        return query.getResultList();
    }
}
