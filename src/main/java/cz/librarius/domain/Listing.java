package cz.librarius.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "LI_LISTING")
@NamedQueries({
        @NamedQuery(name = "findListingByIsbn", query = "select l from Listing l where :isbn in (l.book.ISBNs)"),
})
@SequenceGenerator(name = Listing.SEQ_NAME, sequenceName = Listing.SEQ_NAME)
public class Listing implements Serializable {

    static final String SEQ_NAME = "SEQ_LI_LISTING";

    private Long id;
    private LocalDateTime creationTime;
    private boolean isAutoGraphed = false;
    private Long isbn;
    private Double price;
    private Book book;
    private User user;

    @Id
    @Column(name = "ID_LISTING")
    @GeneratedValue(generator = SEQ_NAME, strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "CREATION_TIME")
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Column(name = "AUTO_GRAPHED")
    public boolean isAutoGraphed() {
        return isAutoGraphed;
    }

    public void setAutoGraphed(boolean autoGraphed) {
        isAutoGraphed = autoGraphed;
    }

    @Column(name = "ISBN")
    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    @Column(name = "PRICE")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_BOOK")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USER")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}