package cz.librarius.controller;

import cz.librarius.domain.User;
import cz.librarius.enums.State;
import cz.librarius.facade.UserFacade;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Model
@RequestScoped
public class UserLoginController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private UserFacade userFacade;

    @Produces
    @Named
    User logUser;

    @PostConstruct
    public void init(){
        logUser = new User();
    }

    public String login(){
        State result = userFacade.login(logUser);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result.toString(), "");
        facesContext.addMessage(null, msg);
        if (result == State.FAIL) return "login";
        else return "home";
    }
}
