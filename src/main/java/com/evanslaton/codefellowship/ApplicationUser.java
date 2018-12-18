package com.evanslaton.codefellowship;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;

@Entity
public class ApplicationUser {

    // Instance properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public String bio;
    public String username;
    public String password;

    // Generic Constructor
    ApplicationUser() {};

    // Constructor
    public ApplicationUser(String firstName, String lastName, String dateOfBirth, String bio, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.username = username;
        this.password = password;
    }
}
