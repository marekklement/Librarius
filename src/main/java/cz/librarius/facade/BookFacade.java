package cz.librarius.facade;

import java.util.List;

import cz.librarius.domain.Listing;
import cz.librarius.enums.State;
import cz.librarius.utils.BookFilter;

public interface BookFacade {

    List<Listing> getAllListings();

    List<Listing> findListingsByFilter(BookFilter bookFilter);

    State updateListing(Listing listing);

    State createListing(Listing listing);

    State removeListing(long id);

    Listing getListing(long id);


}