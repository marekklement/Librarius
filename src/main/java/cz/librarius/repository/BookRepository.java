package cz.librarius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;

import cz.librarius.domain.Book;

@Eager
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByTitle(String title);

    Book findBookByTitleAndIsbn(String title, Long isbn);
}