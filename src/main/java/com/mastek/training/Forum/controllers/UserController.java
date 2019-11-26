package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.model.Thread;
import com.mastek.training.Forum.model.User;
import com.mastek.training.Forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping("/user")
    public User addNewUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }


}
