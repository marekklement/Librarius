package cz.librarius.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import cz.librarius.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByTitle(String title);
}