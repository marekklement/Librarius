package cz.librarius.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import cz.librarius.domain.Book;
import cz.librarius.repository.BookRepository;

@Transactional
@ApplicationScoped
public class BookServiceImpl implements BookService {

    @Inject
    private BookRepository bookRepository;

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findByISBN(String ISBN) {
        return null;
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findAllByTitle(title);
    }
}