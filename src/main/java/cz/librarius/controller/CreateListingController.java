package cz.librarius.controller;


import cz.librarius.domain.Author;
import cz.librarius.domain.Book;
import cz.librarius.domain.User;
import cz.librarius.domain.Listing;
import cz.librarius.enums.BookCategory;
import cz.librarius.enums.State;
import cz.librarius.facade.BookFacade;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
public class CreateListingController implements Serializable {

    @Inject
    private FacesContext facesContext;

    @Inject
    private BookFacade bookFacade;

    @Inject
    private Logger logger;

    @Produces
    @Named
    private Listing newListing;

    @Produces
    @Named
    private Book book;

    @ManagedProperty("#{userLoginController.logUser}")
    private User loggedUser;

    private String author, selectedCategory;
    private BookCategory[] cathegories = BookCategory.values();

    @PostConstruct
    public void init(){
        newListing = new Listing();
        book = new Book();
    }

    public void createListing(){
        logger.info("Creating listing " + book.getTitle() + " with user " + loggedUser.getUsername());
        Author newAuthor = new Author();
        newAuthor.setName(author);
        List<Author> authors = new ArrayList<>();
        authors.add(newAuthor);
        book.setAuthors(authors);
        Set<BookCategory> categories = new HashSet<>();
        categories.add(BookCategory.valueOf(selectedCategory));
        book.setBookCategories(categories);
        newListing.setBook(book);
        newListing.setUser(loggedUser);
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

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public BookCategory[] getCathegories() {
        return cathegories;
    }

    public void setCathegories(BookCategory[] cathegories) {
        this.cathegories = cathegories;
    }
}
