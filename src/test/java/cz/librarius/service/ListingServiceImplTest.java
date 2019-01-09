package cz.librarius.service;

import cz.librarius.domain.Author;
import cz.librarius.domain.Book;
import cz.librarius.domain.Listing;
import cz.librarius.domain.User;
import cz.librarius.enums.BookCategory;
import cz.librarius.repository.dao.AuthorRepository;
import cz.librarius.repository.dao.BookRepository;
import cz.librarius.repository.dao.ListingRepository;
import cz.librarius.repository.dao.UserRepository;
import cz.librarius.utils.BookFilter;
import cz.librarius.utils.Resource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ListingServiceImplTest {

	@Inject
	private UserRepository userRepository;
	@Inject
	private AuthorRepository authorRepository;
	@Inject
	private BookRepository bookRepository;
	@Inject
	private ListingRepository listingRepository;
	@Inject
	private ListingServiceImpl listingService;

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(Listing.class.getPackage())
				.addPackage(ListingService.class.getPackage())
				.addPackage(ListingRepository.class.getPackage())
				.addPackage(Author.class.getPackage())
				.addPackage(AuthorService.class.getPackage())
				.addPackage(AuthorRepository.class.getPackage())
				.addPackage(User.class.getPackage())
				.addPackage(UserService.class.getPackage())
				.addPackage(UserRepository.class.getPackage())
				.addPackage(Book.class.getPackage())
				.addPackage(BookService.class.getPackage())
				.addPackage(BookRepository.class.getPackage())
				.addPackage(ListingServiceImpl.class.getPackage())
				.addPackage(BookCategory.class.getPackage())
				.addPackage(BookFilter.class.getPackage())
				.addClass(Resource.class)
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@Transactional
	public void findListing(){
		User user = new User();
		user.setName("Arnold");
		user.setSurname("Prachar");
		user.setUsername("arnold@gmail.com");
		user.setPassword("rychlyPrachy001");
		user.setLastLoginDate(LocalDate.now());
		user.setRegistrationDate(LocalDate.now());
		user = userRepository.save(user);
		//
		Author author = new Author();
		author.setName("Marek Klement");
		author = authorRepository.save(author);
		Book book = new Book();
		book.setAuthors(Collections.singletonList(author));
		book.setTitle("Krasna rana pod psa!");
		book = bookRepository.save(book);
		//
		Listing listing = new Listing();
		listing.setUser(user);
		listing.setBook(book);
		listing.setIsbn(9780000000000L);
		listing.setPrice(22222.50);
		listing = listingRepository.save(listing);
		Listing foundListing = listingService.findById(listing.getId());
		assertNotNull(foundListing);
		assertEquals(listing.getId(),foundListing.getId());
		//
	}

}