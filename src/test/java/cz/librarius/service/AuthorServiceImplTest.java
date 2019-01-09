package cz.librarius.service;

import cz.librarius.domain.Author;
import cz.librarius.enums.BookCategory;
import cz.librarius.repository.dao.AuthorRepository;
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

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class AuthorServiceImplTest {

	@Inject
	private AuthorRepository authorRepository;
	@Inject
	private AuthorServiceImpl authorService;

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(Author.class.getPackage())
				.addPackage(AuthorRepository.class.getPackage())
				.addPackage(BookCategory.class.getPackage())
				.addPackage(BookFilter.class.getPackage())
				.addPackage(AuthorServiceImpl.class.getPackage())
				.addClass(Resource.class).addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@Transactional
	public void findAuthorTest(){
		Author author = new Author();
		author.setName("Marek Klement");
		author = authorRepository.save(author);
		Author authorFound = authorService.findByUsername(author.getName());
		assertNotNull(authorFound);
		assertEquals(author.getId(),authorFound.getId());
	}

}