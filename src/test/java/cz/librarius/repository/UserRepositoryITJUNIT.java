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
//import cz.librarius.domain.User;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:datasource.xml")
//public class UserRepositoryITJUNIT {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    public void test() {
//        User user = new User();
//        user.setLastLoginDate(LocalDate.now());
//        user.setName("Michal");
//        user.setPassword("Nepoviem");
//        user.setRegistrationDate(LocalDate.now());
//        user.setSurname("Moje");
//        user.setUsername("michnep");
//
//        userRepository.saveAndFlush(user);
//
//        User fetched = userRepository.findById(user.getUsername()).orElse(null);
//
//       assertNotNull(fetched);
//    }
//}