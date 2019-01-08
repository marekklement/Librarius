package cz.librarius.controller;


import cz.librarius.domain.Book;
import cz.librarius.domain.User;
import cz.librarius.domain.Listing;
import cz.librarius.enums.State;
import cz.librarius.facade.BookFacade;

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
public class CreateListingController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private BookFacade bookFacade;


    @Produces
    @Named
    private Listing newListing;

    @Produces
    @Named
    private Book book;

    private String author;

    @PostConstruct
    public void init(){
        newListing = new Listing();
        book = new Book();
    }

    public void createListing(User user){
        newListing.setBook(book);
        newListing.setUser(user);
        State result = bookFacade.createListing(newListing);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result.toString(), "");
        facesContext.addMessage(null, msg);
        init();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
