package cz.librarius.facade;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import cz.librarius.domain.Listing;
import cz.librarius.enums.State;
import cz.librarius.service.ListingService;
import cz.librarius.utils.BookFilter;


public class BookFacadeImpl implements BookFacade {

    @Inject
    ListingService listingService;

    @Override
    public List<Listing> getAllListings() {
        return listingService.findAllListings();
    }

    @Override
    public List<Listing> findListingsByFilter(BookFilter bookFilter) {
        return null;
    }

    @Override
    public State updateListing(Listing listing) {
        listingService.updateListing(listing);

        return State.OK;
    }

    @Override
    public State createListing(Listing listing) {
        listingService.addListing(listing);

        return State.OK;
    }

    @Override
    public State removeListing(long id) {
        Listing foundListing = listingService.findById(id);
        State state = State.OK;

        if (foundListing == null) {
            state = State.FAIL_LISTING_NOT_EXIST;
        }

        return state;
    }

    @Override
    public Listing getListing(long id) {
        return listingService.findById(id);
    }
}