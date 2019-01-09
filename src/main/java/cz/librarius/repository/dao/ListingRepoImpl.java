package cz.librarius.repository.dao;

import cz.librarius.domain.Listing;

import javax.persistence.TypedQuery;

import java.util.List;

public class ListingRepoImpl extends GenericRepository<Listing> implements ListingRepository {

    @Override
    public List<Listing> findAllByIsbn(Long isbn) {
        TypedQuery<Listing> query = em
            .createNamedQuery("findListingByIsbn", Listing.class)
            .setParameter("isbn", isbn);
        return query.getResultList();
    }

    @Override
    public List<Listing> findAllByInvalidatedIsFalse() {
        TypedQuery<Listing> listingTypedQuery = em.createNamedQuery(Listing.FIND_ALL_BY_INVALIDATED_IS_FALSE, Listing.class);

        return listingTypedQuery.getResultList();
    }

    @Override
    public List<Listing> findByFilter(Double isbn, String title, String author) {
        TypedQuery<Listing> listingTypedQuery = em.createNamedQuery(Listing.FIND_BY_FILTER, Listing.class)
            .setParameter("isbn", isbn)
            .setParameter("title", title)
            .setParameter("author", author);

        return listingTypedQuery.getResultList();
    }
}
