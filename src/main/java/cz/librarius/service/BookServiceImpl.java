package cz.librarius.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import cz.librarius.domain.Book;
import cz.librarius.repository.BookRepository;

@Stateless
public class BookServiceImpl implements BookService {

    @Inject
    private BookRepository bookRepository;

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.list();
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