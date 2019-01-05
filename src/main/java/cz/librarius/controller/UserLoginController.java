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
    User user;

    @PostConstruct
    public void init(){
        user = new User();
    }

    public void login(){
        State result = userFacade.login(user);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result.toString(), "");
        facesContext.addMessage(null, msg);
        init();
    }
}
