package cz.librarius.repository.dao;

import cz.librarius.domain.User;


public interface UserRepository extends Repository<User> {

    User findUserByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}