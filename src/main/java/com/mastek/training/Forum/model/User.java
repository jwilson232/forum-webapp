package com.mastek.training.Forum.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String username;
    private Date dob;

    public User(String username, Date dob) {
        this.username = username;
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
