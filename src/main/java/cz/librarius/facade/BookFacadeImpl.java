package cz.librarius.facade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;

import cz.librarius.domain.Author;
import cz.librarius.domain.Book;
import cz.librarius.domain.Listing;
import cz.librarius.domain.User;
import cz.librarius.enums.State;
import cz.librarius.service.AuthorService;
import cz.librarius.service.BookService;
import cz.librarius.service.ListingService;
import cz.librarius.service.UserService;
import cz.librarius.utils.BookFilter;

@Transactional
public class BookFacadeImpl implements BookFacade {

    @Inject
    private ListingService listingService;
    @Inject
    private BookService bookService;
    @Inject
    private AuthorService authorService;
    @Inject
    private UserService userService;
    @Inject
    private Logger logger;

    @Override
    public List<Listing> getAllListings() {
        return listingService.findAllListings();
    }

    @Override
    public List<Listing> findListingsByFilter(BookFilter bookFilter) {
        return listingService.findByFilter(bookFilter);
    }

    @Override
    public State updateListing(Listing listing) {
        listingService.updateListing(listing);

        return State.OK;
    }

    @Override
    public State createListing(Listing listing) {
        logger.info(
            "Create listing(Facade) " + " BookName " + listing.getBook().getTitle() + " Author " + listing.getBook().getAuthors().get(0).getName() + " Price "
            + listing.getPrice().toString());

        Author fetchedAuthor = authorService.findByUsername(listing.getBook().getAuthors().get(0).getName());
        Book fetchedBook = bookService.findBook(listing.getBook().getTitle(), listing.getIsbn());
        User user = userService.findUser(listing.getUser().getUsername(), listing.getUser().getPassword());

        if (fetchedBook == null) {
            listing.getBook().setIsbn(listing.getIsbn());
        } else {
            listing.setBook(fetchedBook);
        }

        if (fetchedAuthor != null) {
            List<Author> authors = new ArrayList<>();
            authors.add(fetchedAuthor);

            listing.getBook().setAuthors(authors);
        }
        listing.setUser(user);
        listing.setCreationTime(LocalDateTime.now());

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