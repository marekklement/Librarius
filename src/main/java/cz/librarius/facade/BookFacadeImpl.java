package cz.librarius.facade;

import java.util.List;

import cz.librarius.domain.Listing;
import cz.librarius.enums.State;
import cz.librarius.utils.BookFilter;

public class BookFacadeImpl implements BookFacade {

    @Override
    public List<Listing> getAllListings() {
        return null;
    }

    @Override
    public List<Listing> findListingsByFilter(BookFilter bookFilter) {
        return null;
    }

    @Override
    public State updateListing(long id) {
        return null;
    }

    @Override
    public State createListing(Listing listing) {
        return null;
    }

    @Override
    public State removeListing(long id) {
        return null;
    }
}