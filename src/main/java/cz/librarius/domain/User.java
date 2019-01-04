package cz.librarius.domain;

import org.springframework.data.domain.Persistable;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "LI_USER")
@SequenceGenerator(name = User.SEQ_NAME, sequenceName = User.SEQ_NAME, allocationSize = 1)
public class User implements Persistable<Long> {

    static final String SEQ_NAME = "SEQ_LI_USER";

    private Long id;
    private String name;
    private String password;
    private String surname;
    private String username;
    private LocalDate registrationDate;
    private LocalDate lastLoginDate;

    @Id
    @Column(name = "ID_USER", nullable = false)
    @GeneratedValue(generator = SEQ_NAME, strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    @Override
    @Transient
    public boolean isNew() {
        return id == null;
    }

    public void setId(Long id) {
        this.id = id;
    }

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