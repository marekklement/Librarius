package cz.librarius.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User findUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public boolean isExistUser(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        return user.getPassword().equals(password);
    }


}