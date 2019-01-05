package cz.librarius.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.librarius.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}