package cz.librarius.service;

import java.util.List;

import cz.librarius.domain.Listing;
import cz.librarius.enums.State;
import cz.librarius.utils.BookFilter;

public interface ListingService {

    void addListing(Listing listing);

    List<Listing> findAllByISBN(long ISBN);

    List<Listing> findAllListings();

    Listing findById(long id);

    State updateListing(Listing listing);

    List<Listing> findByFilter(BookFilter bookFilter);

    void removeListing(Listing listing);
}