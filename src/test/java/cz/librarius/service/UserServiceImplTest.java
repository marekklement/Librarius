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

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class UserServiceImplTest {

	@Inject
	private UserRepository userRepository;
	@Inject
	private UserServiceImpl userService;

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
	public void findUser(){
		User user = new User();
		user.setName("Petr");
		user.setSurname("Bachovka");
		user.setUsername("petr@gmail.com");
		user.setPassword("rychlyPrachy002");
		user.setLastLoginDate(LocalDate.now());
		user.setRegistrationDate(LocalDate.now());
		user = userRepository.save(user);
		User userFound = userService.findUser(user.getUsername(),user.getPassword());
		assertNotNull(userFound);
		assertEquals(user.getLastLoginDate(),userFound.getLastLoginDate());
	}

}