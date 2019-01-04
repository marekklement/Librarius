package cz.librarius.facade;

import java.util.List;

import cz.librarius.domain.Book;
import cz.librarius.domain.User;
import cz.librarius.enums.State;

public interface UserFacade {

    State register(User user);

    State login(User user);
}