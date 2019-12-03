package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.common.Constants;
import com.mastek.training.Forum.model.User;
import com.mastek.training.Forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mastek.training.Forum.common.Constants.Common.*;
import static com.mastek.training.Forum.common.Constants.URIPaths.USER_ID;

@RestController
@RequestMapping(USERS)
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(CREATE)
    public User addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
        return user;
    }

    @DeleteMapping(DELETE + USER_ID)
    public String deleteUser(@PathVariable(Constants.URIParams.USER_ID) String userId) {
        return userService.deleteUser(userId);
    }


}
