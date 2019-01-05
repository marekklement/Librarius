package cz.librarius.facade;

import cz.librarius.domain.User;
import cz.librarius.enums.State;

public interface UserFacade {

    State register(User user);

    State login(User user);
}