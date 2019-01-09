package cz.librarius.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "LI_LISTING")
@NamedQueries({
    @NamedQuery(name = "findListingByIsbn", query = "select l from Listing l where :isbn in (l.book.isbn)"),
    @NamedQuery(name = Listing.FIND_ALL_BY_INVALIDATED_IS_FALSE, query = "select l from Listing l where l.invalidated = FALSE"),
    @NamedQuery(name = Listing.FIND_BY_FILTER, query = "select l from Listing l "
                                                       + "join l.book b "
                                                       + "join b.authors a "
                                                       + "where (:price is not null and l.price >= :price) AND "
                                                       + "(:title = '' or b.title = :title) AND "
                                                       + "(:author = '' or a.name = :author) "),

})
@SequenceGenerator(name = Listing.SEQ_NAME, sequenceName = Listing.SEQ_NAME)
public class Listing implements Serializable {

    public static final String FIND_ALL_BY_INVALIDATED_IS_FALSE = "findAllByInvalidatedIsFalse";
    public static final String FIND_BY_FILTER = "findByFilter";
    static final String SEQ_NAME = "SEQ_LI_LISTING";

    private Long id;
    private LocalDateTime creationTime;
    private boolean invalidated = false;
    private boolean isAutoGraphed = false;
    @NotNull
    @Min(value = 9780000000000L, message = "ISBN is not valid")
    @Max(value = 9799999999999L, message = "ISBN is not valid")
    private Long isbn;
    @NotNull
    @Digits(integer = 20, fraction = 2)
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

    @Column(name = "INVALIDATED")
    public boolean isInvalidated() {
        return invalidated;
    }

    public void setInvalidated(boolean invalidated) {
        this.invalidated = invalidated;
    }
}