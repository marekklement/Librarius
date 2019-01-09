package cz.librarius.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import cz.librarius.enums.BookCategory;

@Entity
@Table(name = "LI_BOOK")
@NamedQueries({
        @NamedQuery(name = "findBookByTitle", query = "select b from Book b where b.title = :title"),
})
@SequenceGenerator(name = Book.SEQ_NAME, sequenceName = Book.SEQ_NAME)
public class Book implements Serializable {

    static final String SEQ_NAME = "SEQ_LI_BOOK";

    private Long id;
    private String language;
    private String title;
    private Long isbn;
    private Set<BookCategory> bookCategories;
    private List<Author> authors;

    @Id
    @Column(name = "ID_BOOK")
    @GeneratedValue(generator = SEQ_NAME, strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "LANGUAGE")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    @ElementCollection(fetch = FetchType.LAZY)
//    @CollectionTable(
//        name = "LI_BOOK_ISBN",
//        joinColumns = @JoinColumn(name = "ID_ISBN", nullable = false)
//    )
    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "LI_BOOK_CATEGORY",
        joinColumns = @JoinColumn(name = "ID_BOOK_CATEGORY", nullable = false)
    )
    public Set<BookCategory> getBookCategories() {
        if (bookCategories == null) {
            bookCategories = new HashSet<>();
        }

        return bookCategories;
    }

    public void setBookCategories(Set<BookCategory> bookCategories) {
        this.bookCategories = bookCategories;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "LI_BOOK_AUTH",
        joinColumns = @JoinColumn(name = "ID_BOOK", referencedColumnName = "ID_BOOK"),
        inverseJoinColumns = @JoinColumn(name = "ID_AUTHOR", referencedColumnName = "ID_AUTHOR")
    )
    public List<Author> getAuthors() {
        if (authors == null) {
            authors = new ArrayList<>();
        }

        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}