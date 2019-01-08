package cz.librarius.repository;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.transaction.Transactional;

import cz.librarius.domain.User;
import cz.librarius.enums.BookCategory;
import cz.librarius.utils.Resource;

@RunWith(Arquillian.class)
@Transactional
public class UserRepositoryIT {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage(User.class.getPackage())
            .addPackage(BookCategory.class.getPackage())
            .addPackage(UserRepository.class.getPackage())
            .addClass(UserRepository.class)
            .addClass(Resource.class)
            .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private UserRepository userRepository;

    @Test
    public void testPropertyMappings() {
        User user = new User();
        user.setName("Name");
        user.setSurname("Surname");
        user.setPassword("Password");
        user.setUsername("username");
        user.setRegistrationDate(LocalDate.now());

    }
}