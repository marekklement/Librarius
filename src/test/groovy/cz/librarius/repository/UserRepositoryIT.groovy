package cz.librarius.repository

import cz.librarius.domain.User
import cz.librarius.repository.dao.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.time.LocalDate

@SpringBootTest
@ContextConfiguration("classpath:datasource.xml")
class UserRepositoryIT extends Specification {

    @Autowired
    UserRepository userRepository

    def setup() {
        assert userRepository != null
    }

    def "test property mappings"() {
        given: "Construct User"
            def user = new User(
                    name: "Jan",
                    password: "nepoviem",
                    surname: "Mrkvicka",
                    username: "jan.mrkvicka@gmail.com",
                    registrationDate: LocalDate.now(),
                    lastLoginDate: LocalDate.now()
            )
        when: "saving to DB"
            userRepository.save(user)
        then: "force flush INSERT statement to DB and clear PersistenceContext -> entity is detached!"
            flushAndClearEntityManager()
        when: "fetch entity"
            def fetched = userRepository.findById(user.id).orElse(null)
        then: "Check the data was correctly saved and mapped back to entity"
            fetched.id == user.id
            fetched.name == user.name
            fetched.password == user.password
            fetched.surname == user.surname
            fetched.username == user.username
            fetched.registrationDate == user.registrationDate
            fetched.lastLoginDate == user.lastLoginDate
    }
}
