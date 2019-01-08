package cz.librarius.repository;

import cz.librarius.domain.Listing;

import javax.persistence.TypedQuery;
import java.util.List;

public class ListingRepoImpl extends GenericRepository<Listing> implements ListingRepository {
    @Override
    public List<Listing> findAllByIsbn(String isbn) {
        TypedQuery<Listing> query = em
                .createNamedQuery("findListingByIsbn", Listing.class)
                .setParameter("isbn", isbn);
        return query.getResultList();
    }
}
