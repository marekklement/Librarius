package cz.librarius.service;

import java.util.List;

import cz.librarius.domain.Book;
import cz.librarius.repository.BookRepository;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

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