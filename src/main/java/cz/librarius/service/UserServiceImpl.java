package cz.librarius.service;

import java.time.LocalDate;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import cz.librarius.domain.User;
import cz.librarius.repository.UserRepository;

@Transactional
@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private Logger logger;

    @Inject
    private UserRepository userRepository;

    @Override
    public void register(User user) {
        logger.info("save user " + user.getUsername() + " " + user.getPassword());
        userRepository.save(user);
    }

    @Override
    public User findUser(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public boolean isExistUser(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public void updateLoginDate(String username, LocalDate localDate) {
        User foundUser = userRepository.findByUsername(username);
        foundUser.setLastLoginDate(localDate);

        userRepository.save(foundUser);
    }


}