package com.trainibit.labs.spring_boot_hibernate_crud.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
//import org.springframework.context.annotation.Profile; Esta cosa causa error

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;

    //mapea una relación uno a uno entre User y Profile.
    @OneToOne(mappedBy = "user", cascade = jakarta.persistence.CascadeType.ALL) //propaga los cambios de User a Profile.

    @Cascade(CascadeType.ALL) // Cascade operation will be handled by Hibernate
    private Profile profile;

    // Constructors, getters, and setters
    public User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
    // Getters and setters
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public Profile getProfile() {return profile;}
    public void setProfile(Profile profile) {this.profile = profile;}
}

