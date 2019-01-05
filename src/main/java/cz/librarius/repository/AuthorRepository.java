package cz.librarius.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.librarius.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}