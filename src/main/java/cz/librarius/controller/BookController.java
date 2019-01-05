package cz.librarius.controller;

import cz.librarius.domain.Book;
import cz.librarius.domain.Listing;
import cz.librarius.enums.BookCategory;
import cz.librarius.enums.State;
import cz.librarius.facade.BookFacade;
import cz.librarius.utils.BookFilter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Model
@RequestScoped
public class BookController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private BookFacade bookFacade;

    private String isbn, title, author;
    private String selectedCategory;
    private Long selectedListing;
    private Double newPrice;
    private Listing listing;
    private BookCategory[] cathegories = BookCategory.values();
    private List<Listing> bookList;

    @PostConstruct
    public void init(){
        bookList = bookFacade.getAllListings();
    }

    public void filter(){
        bookList = bookFacade.findListingsByFilter(new BookFilter(isbn, title, author, BookCategory.valueOf(selectedCategory)));
    }

    public String getListingDetail(){
        listing = bookFacade.getListing(selectedListing);
        return "book-detail.xhtml";
    }

    public void updateBook(){
        listing.setPrice(newPrice);
        State result = bookFacade.updateListing(listing);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result.toString(), "");
        facesContext.addMessage(null, msg);
    }
    
    public void deleteBook(){
        bookFacade.removeListing(selectedListing);
        State result = bookFacade.updateListing(listing);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result.toString(), "");
    }
}
