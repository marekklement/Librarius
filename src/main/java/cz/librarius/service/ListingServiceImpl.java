package cz.librarius.service;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;

import cz.librarius.domain.Listing;
import cz.librarius.enums.State;
import cz.librarius.repository.dao.ListingRepository;
import cz.librarius.utils.BookFilter;

import static java.util.stream.Collectors.toList;

@Transactional
public class ListingServiceImpl implements ListingService {

//    @Inject
//    private ListingRepository listingRepository;
    @Inject
    private ListingRepository listingRepository;
    @Inject
    private Logger logger;

    @Override
    public void addListing(Listing listing) {
        listingRepository.save(listing);
    }

    @Override
    public List<Listing> findAllByISBN(long isbn) {
        return listingRepository.findAllByIsbn(isbn);
    }

    @Override
    public List<Listing> findAllListings() {
        List<Listing> listings = listingRepository.findAllByInvalidatedIsFalse();
        logger.info("FindAllListings " + listings.size());
        return listings;
    }

    @Override
    public Listing findById(long id) {
        return listingRepository.find(id);
    }

    @Override
    public State updateListing(Listing listing) {
        Listing foundListing = listingRepository.find(listing.getId());
        State state = State.OK;

        if (foundListing != null && !listing.isInvalidated()) {
            logger.info("Invalidated listing");
            foundListing.setUser(listing.getUser());
            foundListing.setIsbn(listing.getIsbn());
            foundListing.setBook(listing.getBook());
            foundListing.setCreationTime(listing.getCreationTime());
            foundListing.setAutoGraphed(listing.isAutoGraphed());
            foundListing.setPrice(listing.getPrice());

            listingRepository.update(foundListing);
        } else {
            logger.info("listing not exist");
            state = State.FAIL_LISTING_NOT_EXIST;
        }

        return state;
    }

    @Override
    public List<Listing> findByFilter(BookFilter bookFilter) {
        List<Listing> listings = listingRepository.findByFilter(bookFilter.getIsbn(), bookFilter.getTitle(), bookFilter.getAuthor());
        listings = listings.stream()
            .filter(listing -> listing.getBook().getBookCategories().contains(bookFilter.getBookCategory()))
            .collect(toList());

        return listings;
    }

    @Override
    public void removeListing(Listing listing) {
        listing.setInvalidated(true);
        listingRepository.update(listing);
    }

}