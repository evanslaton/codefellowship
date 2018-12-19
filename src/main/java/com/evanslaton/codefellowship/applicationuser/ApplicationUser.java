package com.evanslaton.codefellowship.applicationuser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

@Entity
public class ApplicationUser implements UserDetails {

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

    // UserDetails interface methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return this.firstName + " '" + this.username + "' " + this.lastName;
    }
}
