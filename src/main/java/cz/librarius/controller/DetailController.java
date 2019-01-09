package cz.librarius.controller;

import cz.librarius.domain.Listing;
import cz.librarius.enums.State;
import cz.librarius.facade.BookFacade;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class DetailController {

    @Inject
    private Logger logger;

    @Inject
    private FacesContext facesContext;

    @Inject
    private BookFacade bookFacade;

    private Listing listing;

    @PostConstruct
    public void init(){
    }

    public String showListingDetail(Long selectedId){
        logger.info("Redirecting to detail of listing " + selectedId);
        listing = bookFacade.getListing(selectedId);
        return "book-detail";
    }

    public void updateListing() {
        logger.info("Updating listing " + listing.getBook().getTitle());
        State result = bookFacade.updateListing(listing);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result.toString(), "");
        facesContext.addMessage(null, msg);
    }

    public String deleteListing() {
        logger.info("Deleting listing " + listing.getBook().getTitle());
        State result = bookFacade.removeListing(listing.getId());
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result.toString(), "");
        facesContext.addMessage(null, msg);
        if (result == State.FAIL_LISTING_NOT_EXIST) return "book-detail";
        else return "books";
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }
}
