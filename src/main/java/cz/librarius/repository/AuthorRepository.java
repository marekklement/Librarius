package cz.librarius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

import cz.librarius.domain.Author;

@Eager
public interface AuthorRepository extends JpaRepository<Author, Long> {
}