package cz.librarius.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import cz.librarius.domain.Listing;
import cz.librarius.repository.ListingRepository;
import cz.librarius.utils.BookFilter;

@Stateless
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
        return listingRepository.list();
    }

    @Override
    public Listing findById(long id) {
        return listingRepository.find(id);
    }

    @Override
    public void updateListing(Listing listing) {
        Listing foundListing = listingRepository.find(listing.getId());

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


        return null;
    }

}