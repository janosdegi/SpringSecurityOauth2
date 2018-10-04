package com.oauth2.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dégi János on 2018.03.01..
 */
@Entity
@Table(name="privilege", schema = "public")
public class Privilege implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy="privileges")
    private Set<Role> roles = new HashSet<Role>();

    public Privilege() {
        super();
    }

    public Privilege(String name) {
        this.name = name;
    }

    //______________


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
