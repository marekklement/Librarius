package cz.librarius.service;

import javax.inject.Inject;
import javax.transaction.Transactional;

import cz.librarius.domain.Author;
import cz.librarius.repository.AuthorRepository;

@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Inject
    private AuthorRepository authorRepository;

    @Override
    public Author findByUsername(String name) {
        return authorRepository.findAuthorByName(name);
    }
}