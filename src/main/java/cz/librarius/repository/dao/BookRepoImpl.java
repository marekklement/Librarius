package cz.librarius.repository.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import cz.librarius.domain.Book;

public class BookRepoImpl extends GenericRepository<Book> implements BookRepository {

    @Override
    public List<Book> findAllByTitle(String title) {
        TypedQuery<Book> query = em
            .createNamedQuery("findBookByTitle", Book.class)
            .setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public Book findBookByTitleAndIsbn(String title, Long isbn) {
        TypedQuery<Book> bookTypedQuery = em.createNamedQuery(Book.FIND_BOOK_BY_TITLE_AND_ISBN, Book.class)
            .setParameter("title", title)
            .setParameter("isbn", isbn);

        List<Book> books = bookTypedQuery.getResultList();
        if (books != null && books.size() > 0) {
            return books.get(0);
        } else {
            return null;
        }
    }
}
