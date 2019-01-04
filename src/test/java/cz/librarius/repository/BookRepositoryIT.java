package cz.librarius.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cz.librarius.domain.Author;
import cz.librarius.domain.Book;
import cz.librarius.enums.BookCategory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:datasource.xml")
public class BookRepositoryIT {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void testPropertyMappings() {
        Set<Long> ISBNs = new HashSet<>();
        ISBNs.add(9788000041889L);
        Set<BookCategory> bookCategories = new HashSet<>();
        bookCategories.add(BookCategory.FANTASY);
        List<Author> authors = new ArrayList<>();
        authors.add(createAuthor("Erin Hunter", "USA", LocalDate.of(1990, 2, 23)));

        Book book = new Book();
        book.setLanguage("CZ");
        book.setTitle("Zakon smecky");
        book.setISBNs(ISBNs);
        book.setBookCategories(bookCategories);
        book.setAuthors(authors);

        bookRepository.saveAndFlush(book);

        Book fetched = bookRepository.findById(book.getId()).orElse(null);

        assertNotNull(fetched);
        assertEquals(1, fetched.getAuthors().size());
        assertEquals(book.getTitle(), fetched.getTitle());
    }

    private Author createAuthor(String name, String country, LocalDate birthDate) {
        Author author = new Author();
        author.setBirthDate(birthDate);
        author.setCountry(country);
        author.setName(name);

        return author;
    }
}