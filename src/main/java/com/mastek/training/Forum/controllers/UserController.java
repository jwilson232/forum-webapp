package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.model.User;
import com.mastek.training.Forum.repository.UserRepository;
import com.mastek.training.Forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public User addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
        return user;
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") String userid) {
        return userService.deleteUser(userid);
    }


}
