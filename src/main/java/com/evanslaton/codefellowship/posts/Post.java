package com.evanslaton.codefellowship.posts;

import com.evanslaton.codefellowship.applicationuser.ApplicationUser;

import javax.persistence.*;

@Entity
public class Post {
    // Instance properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String createdAt;
    public String body;

    @ManyToOne
    public ApplicationUser applicationUser;

    // Generic Constructor
    Post(){}

    // Constructor
    Post(String createdAt, String body) {
        this.createdAt = createdAt;
        this.body = body;
    }
}
