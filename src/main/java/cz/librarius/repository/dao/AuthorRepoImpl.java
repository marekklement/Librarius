package cz.librarius.repository.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import cz.librarius.domain.Author;

public class AuthorRepoImpl extends GenericRepository<Author> implements AuthorRepository {


    @Override
    public Author findAuthorByName(String name) {
        TypedQuery<Author> authorTypedQuery = em.createNamedQuery(Author.FIND_AUTHOR_BY_NAME, Author.class)
            .setParameter("name", name);

        List<Author> authors = authorTypedQuery.getResultList();
        if (authors != null && authors.size() > 0) {
            return authors.get(0);
        } else {
            return null;
        }
    }
}
