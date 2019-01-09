package cz.librarius.repository;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.spring.integration.test.annotation.SpringConfiguration;
import org.jboss.arquillian.spring.integration.test.annotation.SpringContextConfiguration;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.transaction.Transactional;

import cz.librarius.domain.Author;
import cz.librarius.domain.Book;
import cz.librarius.domain.Listing;
import cz.librarius.domain.User;
import cz.librarius.enums.BookCategory;
import cz.librarius.repository.dao.UserRepoImpl;
import cz.librarius.utils.Resource;

@RunWith(Arquillian.class)
@Transactional
public class UserRepositoryIT {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage(User.class.getPackage())
            .addPackage(Listing.class.getPackage())
            .addPackage(Book.class.getPackage())
            .addPackage(Author.class.getPackage())
            .addPackage(UserRepoImpl.class.getPackage())
            .addPackage(BookCategory.class.getPackage())
            .addClass(Resource.class)
            .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private UserRepoImpl userRepository;

    @Test
    public void testPropertyMappings() {
        User user = new User();
        user.setName("Name");
        user.setSurname("Surname");
        user.setPassword("Password");
        user.setUsername("username");
        user.setRegistrationDate(LocalDate.now());

        userRepository.save(user);

    }
}