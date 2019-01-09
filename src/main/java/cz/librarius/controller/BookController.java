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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@RequestScoped
public class BookController {

    @Inject
    private Logger logger;

    @Inject
    private FacesContext facesContext;

    @Inject
    private BookFacade bookFacade;

    private String title, author;
    private Double price = 0.0;
    private String selectedCategory;

    private BookCategory[] cathegories = BookCategory.values();
    private List<Listing> bookList;

    @PostConstruct
    public void init() {
        bookList = bookFacade.getAllListings();
    }

    public void filter() {
        logger.info("Filtering books by price " + price + ",title " + title + ",author" + author + " and category " + selectedCategory);
        bookList = bookFacade.findListingsByFilter(new BookFilter(price, title, author, BookCategory.valueOf(selectedCategory)));
    }



    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
