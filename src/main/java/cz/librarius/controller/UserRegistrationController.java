package cz.librarius.controller;


import cz.librarius.domain.User;
import cz.librarius.enums.State;
import cz.librarius.facade.UserFacade;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.logging.Logger;

@ManagedBean
@RequestScoped
public class UserRegistrationController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private UserFacade userFacade;

    @Inject
    private Logger logger;

    private User regUser;

    @PostConstruct
    public void init(){
        regUser = new User();
    }

    public String register(){
        logger.info("Registering user " + regUser.getUsername() + " with name" + regUser.getName() + " " + regUser.getSurname());
        regUser.setLastLoginDate(LocalDate.now());
        regUser.setRegistrationDate(LocalDate.now());
        State result = userFacade.register(regUser);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result.toString(), "");
        facesContext.addMessage(null, msg);
        init();
        if (result == State.FAIL_EXIST_USER) return "registration";
        else return "login";
    }

    public User getRegUser() {
        return regUser;
    }

    public void setRegUser(User regUser) {
        this.regUser = regUser;
    }
}
