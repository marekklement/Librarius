package cz.librarius.service;

import java.util.List;

import cz.librarius.domain.Listing;

public interface ListingService {

    void addListing(Listing listing);

    List<Listing> findAllByISBN(String ISBN);
}