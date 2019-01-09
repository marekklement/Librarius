package cz.librarius.repository.dao;

import java.util.List;

import cz.librarius.domain.Book;


public interface BookRepository extends Repository<Book> {

    List<Book> findAllByTitle(String title);

    Book findBookByTitleAndIsbn(String title, Long isbn);
}