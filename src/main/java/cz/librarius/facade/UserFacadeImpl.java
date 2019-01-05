package cz.librarius.facade;

import java.time.LocalDate;

import cz.librarius.domain.User;
import cz.librarius.enums.State;
import cz.librarius.service.UserService;

public class UserFacadeImpl implements UserFacade {

    private UserService userService;

    @Override
    public State register(User user) {

        State state = State.OK;

        if (userService.isExistUser(user.getUsername())) {
            state = State.FAIL_EXIST_USER;
        } else {
            userService.register(user);
        }

        return state;
    }

    @Override
    public State login(User user) {
        User existUser = userService.findUser(user.getUsername(), user.getPassword());
        State state = State.FAIL;

        if (existUser != null) {
            userService.updateLoginDate(user.getUsername(), LocalDate.now());
            state = State.OK;
        }

        return state;
    }
}