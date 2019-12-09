package com.mastek.training.Forum.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "user")
@Data
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String username;
    private Date dob;
    private String email;
    private String gmailId;
    private String firstName;
    private String lastName;

    public User(String username, String email, String gmailId, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.gmailId = gmailId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
