package cz.librarius.service;


import cz.librarius.domain.Author;

public interface AuthorService {

    Author findByUsername(String name);
}