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

    public User(String username, Date dob) {
        this.username = username;
        this.dob = dob;
    }

}
