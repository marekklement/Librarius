package cz.librarius.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedQueries({
    @NamedQuery(name = Author.FIND_AUTHOR_BY_NAME,
        query = "select a from Author a where a.name = :name")
})
@Entity
@Table(name = "LI_AUTHOR")
@SequenceGenerator(name = Author.SEQ_NAME, sequenceName = Author.SEQ_NAME)
public class Author implements Serializable {

    public static final String FIND_AUTHOR_BY_NAME = "findAuthorByName";
    static final String SEQ_NAME = "SEQ_LI_AUTHOR";

    private Long id;
    private LocalDate birthDate;
    private String country;
    @NotNull
//    @Pattern(regexp = "[A-Za-z]*", message = "must contain only letters and spaces")
    @Size(min = 1, max = 255)
    private String name;

    @Id
    @Column(name = "ID_AUTHOR")
    @GeneratedValue(generator = SEQ_NAME, strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "BIRTH_DATE")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}