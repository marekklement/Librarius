package cz.librarius.service;

import cz.librarius.domain.Author;
import cz.librarius.domain.Book;
import cz.librarius.enums.BookCategory;
import cz.librarius.repository.dao.AuthorRepository;
import cz.librarius.repository.dao.BookRepository;
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
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BookServiceImplTest {

	@Inject
	private BookServiceImpl bookService;
	@Inject
	private AuthorRepository authorRepository;
	@Inject
	private BookRepository bookRepository;

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(Book.class.getPackage())
				.addPackage(BookServiceImpl.class.getPackage())
				.addPackage(Author.class.getPackage())
				.addPackage(AuthorRepository.class.getPackage())
				.addPackage(Author.class.getPackage())
				.addPackage(BookRepository.class.getPackage())
				.addPackage(BookCategory.class.getPackage())
				.addPackage(BookFilter.class.getPackage())
				.addClass(Resource.class)
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}


	@Test
	@Transactional
	public void saveBookTest(){
		Author author = new Author();
		author.setName("Marek Klement");
		author = authorRepository.save(author);
		Book book = new Book();
		book.setAuthors(Collections.singletonList(author));
		book.setTitle("Krasna rana pod psa!");
		book = bookRepository.save(book);
		assertNotNull(book.getId());
		List<Book> books = bookService.findByTitle(book.getTitle());
		assertNotNull(books);
		assertEquals(1,books.size());
		assertEquals(book.getId(),books.get(0).getId());
	}
}