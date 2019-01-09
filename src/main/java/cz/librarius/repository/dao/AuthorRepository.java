package cz.librarius.repository.dao;

import cz.librarius.domain.Author;


public interface AuthorRepository extends Repository<Author> {

    Author findAuthorByName(String name);
}