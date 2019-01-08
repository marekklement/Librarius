package cz.librarius.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.librarius.domain.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface AuthorRepository extends cz.librarius.repository.Repository<Author> {
}