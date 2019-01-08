package cz.librarius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

import cz.librarius.domain.User;

@Eager
public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}