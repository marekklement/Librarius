package cz.librarius.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import cz.librarius.domain.User;
import cz.librarius.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
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