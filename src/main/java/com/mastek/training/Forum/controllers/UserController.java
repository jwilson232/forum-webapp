package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.model.User;
import com.mastek.training.Forum.repository.UserRepository;
import com.mastek.training.Forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping("/user")
    public User addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
        return user;
    }

    @DeleteMapping("/user")
    public String deleteUser(@RequestParam String userid) {
        return userService.deleteUser(userid);
    }


}
