package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.common.Constants;
import com.mastek.training.Forum.model.User;
import com.mastek.training.Forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.mastek.training.Forum.common.Constants.Common.*;
import static com.mastek.training.Forum.common.Constants.URIPaths.USER_ID;

@Controller
@RequestMapping(USERS)
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Value("${app.thread.title}")
    private String TITLE = "";

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

//    @PostMapping(CREATE)
//    public User addNewUser(@RequestBody User user, OAuth2Authentication oAuth2Authentication) {
//        user.setEmail(userService.googleDetails(oAuth2Authentication).get("email"));
//        user.setGmailId(userService.googleDetails(oAuth2Authentication).get("id"));
//        userService.addNewUser(user);
//        return user;
//    }

    @PostMapping(CREATE)
    public User addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
        return user;
    }

    @DeleteMapping(DELETE + USER_ID)
    public String deleteUser(@PathVariable(Constants.URIParams.USER_ID) String userId) {
        return userService.deleteUser(userId);
    }

    @RequestMapping(PROFILE)
    public String profile(Map<String, Object> model) {
        model.put("title", TITLE);
        return "profile";
    }
}
