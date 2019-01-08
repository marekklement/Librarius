package cz.librarius.controller;

import com.sun.tracing.dtrace.ModuleAttributes;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@ManagedBean
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

    public String showListingDetail(){
        listing = bookFacade.getListing(selectedListing);
        return "book-detail";
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public Long getSelectedListing() {
        return selectedListing;
    }

    public void setSelectedListing(Long selectedListing) {
        this.selectedListing = selectedListing;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public BookCategory[] getCathegories() {
        return cathegories;
    }

    public void setCathegories(BookCategory[] cathegories) {
        this.cathegories = cathegories;
    }

    public List<Listing> getBookList() {
        return bookList;
    }

    public void setBookList(List<Listing> bookList) {
        this.bookList = bookList;
    }
}
