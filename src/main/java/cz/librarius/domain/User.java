package cz.librarius.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
    @NamedQuery(name = "findUserByUsername", query = "select u from User u where u.username = :username"),
    @NamedQuery(name = "findUserByUsernameAndPassword", query = "select u from User u where u.username = :username and u.password = :password")
})
@Table(name = "LI_USER")
public class User implements Serializable {

    static final String SEQ_NAME = "SEQ_LI_USER";

    @Size(max = 255)
    @Pattern(regexp = "[A-Za-z]*", message = "must contain only letters and spaces")
    private String name;
    @NotNull
    @Size(min = 5, max = 255)
    private String password;
    @Size(max = 255)
    @Pattern(regexp = "[A-Za-z]*", message = "must contain only letters and spaces")
    private String surname;
    @NotNull
    @Size(min = 1, max = 255)
    @Email(message = "Email should be valid")
    private String username;
    private LocalDate registrationDate;
    private LocalDate lastLoginDate;

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "PASSWORD", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "SURNAME", nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Id
    @Column(name = "USERNAME", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "REGISTRATION_DATE", nullable = false)
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Column(name = "LAST_LOGIN_DATE", nullable = false)
    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}