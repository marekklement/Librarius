package cz.librarius.service;

import java.util.List;

import cz.librarius.domain.Listing;
import cz.librarius.utils.BookFilter;

public interface ListingService {

    void addListing(Listing listing);

    List<Listing> findAllByISBN(String ISBN);

    List<Listing> findAllListings();

    Listing findById(long id);

    void updateListing(Listing listing);

    List<Listing> findByFilter(BookFilter bookFilter);
}