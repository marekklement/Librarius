package cz.librarius.service;

import cz.librarius.domain.User;

/**
 * Kontrola hesla
 */
public interface UserService {

    void register(User user);

    User findUser(String username);

    boolean isExistUser(String username, String password);
}