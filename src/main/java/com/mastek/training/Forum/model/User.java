package com.mastek.training.Forum.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class User {
    private String id;
    private String username;
    private Date dob;

    public User(String id, String username, Date dob) {
        this.id = id;
        this.username = username;
        this.dob = dob;
    }

}
