package cz.librarius.service;

import java.util.List;

import cz.librarius.domain.Listing;
import cz.librarius.repository.ListingRepository;

public class ListingServiceImpl implements ListingService {

    private final ListingRepository listingRepository;

    public ListingServiceImpl(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    @Override
    public void addListing(Listing listing) {
        listingRepository.save(listing);
    }

    @Override
    public List<Listing> findAllByISBN(String isbn) {
        return listingRepository.findAllByIsbn(isbn);
    }
}