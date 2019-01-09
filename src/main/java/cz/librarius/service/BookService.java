package cz.librarius.service;

import java.util.List;

import cz.librarius.domain.Book;

public interface BookService {

    void addBook(Book book);

    List<Book> getAll();

    Book findByISBN(String ISBN);

    List<Book> findByTitle(String title);

    Book findBook(String title, Long isbn);

}