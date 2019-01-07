//package cz.librarius.repository;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.time.LocalDate;
//
//import cz.librarius.domain.Author;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:datasource.xml")
//public class AuthorRepositoryIT {
//
//    @Autowired
//    AuthorRepository authorRepository;
//
//    @Test
//    public void testPropertyMappings() {
//        Author author = new Author();
//        author.setName("Erin Hunter");
//        author.setCountry("USA");
//        author.setBirthDate(LocalDate.of(1990, 2, 23));
//
//        authorRepository.saveAndFlush(author);
//
//        Author fetched = authorRepository.findById(author.getId()).orElse(null);
//
//        Assert.assertNotNull(fetched);
//    }
//
//
//}