package cz.librarius.facade;

import java.util.List;

import cz.librarius.domain.Book;
import cz.librarius.enums.State;
import cz.librarius.utils.BookFilter;

public interface BookFacade {

    List<Book> getAllBooks();

    List<Book> findBooksByFilter(BookFilter bookFilter);

    State updateBook(String isbn);

    State createBook(Book book);


}