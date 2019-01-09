package cz.librarius.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;

import cz.librarius.domain.Listing;
import cz.librarius.repository.ListingRepository;
import cz.librarius.utils.BookFilter;

import static java.util.stream.Collectors.*;

@Transactional
public class ListingServiceImpl implements ListingService {

    @Inject
    private ListingRepository listingRepository;

    @Override
    public void addListing(Listing listing) {
        listingRepository.save(listing);
    }

    @Override
    public List<Listing> findAllByISBN(String isbn) {
        return listingRepository.findAllByIsbn(isbn);
    }

    @Override
    public List<Listing> findAllListings() {
        return listingRepository.findAll();
    }

    @Override
    public Listing findById(long id) {
        return listingRepository.findById(id).orElse(null);
    }

    @Override
    public void updateListing(Listing listing) {
        Listing foundListing = listingRepository.findById(listing.getId()).orElse(null);

        if (foundListing != null) {
            foundListing.setUser(listing.getUser());
            foundListing.setIsbn(listing.getIsbn());
            foundListing.setBook(listing.getBook());
            foundListing.setCreationTime(listing.getCreationTime());
            foundListing.setAutoGraphed(listing.isAutoGraphed());
            foundListing.setPrice(listing.getPrice());

            listingRepository.save(foundListing);
        }
    }

    @Override
    public List<Listing> findByFilter(BookFilter bookFilter) {
        List<Listing> listings = listingRepository.findByFilter(bookFilter.getIsbn(), bookFilter.getTitle(), bookFilter.getAuthor());
        listings = listings.stream()
            .filter(listing -> listing.getBook().getBookCategories().contains(bookFilter.getBookCategory()))
            .collect(toList());

        return listings;
    }

}