package cz.librarius.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import cz.librarius.domain.Book;
import cz.librarius.domain.Listing;
import cz.librarius.domain.User;
import cz.librarius.enums.BookCategory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:datasource.xml")
public class ListingRepositoryIT {

    @Autowired
    ListingRepository listingRepository;

    @Test
    public void testPropertyMappings() {
        User user = new User();
        user.setLastLoginDate(LocalDate.now());
        user.setName("Michal");
        user.setPassword("Nepoviem");
        user.setRegistrationDate(LocalDate.now());
        user.setSurname("Moje");
        user.setUsername("michnep");

        Set<Long> ISBNs = new HashSet<>();
        ISBNs.add(9788000041889L);
        Set<BookCategory> bookCategories = new HashSet<>();
        bookCategories.add(BookCategory.FANTASY);

        Book book = new Book();
        book.setLanguage("CZ");
        book.setTitle("Zakon smecky");
        book.setISBNs(ISBNs);
        book.setBookCategories(bookCategories);

        Listing listing = new Listing();
        listing.setAutoGraphed(false);
        listing.setCreationTime(LocalDateTime.now());
        listing.setIsbn(9788000041889L);
        listing.setPrice(3.4);
        listing.setBook(book);
        listing.setUser(user);

        listingRepository.saveAndFlush(listing);

        Listing fetched = listingRepository.findById(listing.getId()).orElse(null);

        Assert.assertNotNull(fetched);
    }
}