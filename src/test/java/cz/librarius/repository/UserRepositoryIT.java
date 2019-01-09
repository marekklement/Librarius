//package cz.librarius.repository;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.arquillian.spring.integration.test.annotation.SpringConfiguration;
//import org.jboss.arquillian.spring.integration.test.annotation.SpringContextConfiguration;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.time.LocalDate;
//
//import javax.ejb.EJB;
//import javax.inject.Inject;
//import javax.transaction.Transactional;
//
//import cz.librarius.domain.User;
//import cz.librarius.enums.BookCategory;
//import cz.librarius.utils.Resource;
//
//@RunWith(Arquillian.class)
//@SpringConfiguration("conf/applicationConfig.xml")
//public class UserRepositoryIT {
//
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//            .addClasses(UserRepository.class, User.class, BookCategory.class)
////            .addClass(Resource.class)
//            .addAsResource("conf/applicationConfig.xml");
////            .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
////            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void testPropertyMappings() {
//        User user = new User();
//        user.setName("Name");
//        user.setSurname("Surname");
//        user.setPassword("Password");
//        user.setUsername("username");
//        user.setRegistrationDate(LocalDate.now());
//
//    }
//}