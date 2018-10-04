package com.oauth2.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dégi János on 2017.10.07..
 */
@Entity
@Table(name="verificationtoken")
public class VerificationToken implements Serializable {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="token")
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "username")
    private User user;

    @Column(name="expiritydate")
    private Date expirityDate;

    public VerificationToken() {

    }

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public VerificationToken(String token, User user, Date expirityDate) {
        this.token = token;
        this.user = user;
        this.expirityDate = expirityDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpirityDate() {
        return expirityDate;
    }

    public void setExpirityDate(Date expirityDate) {
        this.expirityDate = expirityDate;
    }
}
