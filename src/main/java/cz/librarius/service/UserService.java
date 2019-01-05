package cz.librarius.service;

import java.time.LocalDate;

import cz.librarius.domain.User;

/**
 * Kontrola hesla
 */
public interface UserService {

    void register(User user);

    User findUser(String username, String password);

    boolean isExistUser(String username);

    void updateLoginDate(String username, LocalDate localDate);
}