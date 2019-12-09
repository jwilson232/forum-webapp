package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.common.Constants;
import com.mastek.training.Forum.model.User;
import com.mastek.training.Forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.mastek.training.Forum.common.Constants.Common.*;
import static com.mastek.training.Forum.common.Constants.URIPaths.USER_ID;

@RequestMapping(USERS)
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Value("${app.thread.title}")
    private String TITLE = "";

    @GetMapping(PROFILE)
    public String getAllUsers(Map<String, Object> model, OAuth2Authentication oAuth2Authentication){
        String gmailId = userService.googleDetails(oAuth2Authentication).get("id");
        String profile = "profile";

        User user = userService.customUserSearch("gmailId", gmailId);
        model.put("title", TITLE);

        if (user == null) {
            profile = "updateprofile";
        } else {
            model.put("firstname", user.getFirstName());
            model.put("lastname", user.getLastName());
            model.put("username", user.getUsername());
            model.put("email", user.getEmail());
        }

        return profile;
    }

    @PostMapping(CREATE)
    public String addNewUser(OAuth2Authentication oAuth2Authentication,
                            @ModelAttribute("User") User user, Map<String, Object> model) {
        String email = userService.googleDetails(oAuth2Authentication).get("email");
        String gmailId = userService.googleDetails(oAuth2Authentication).get("id");

        user.setEmail(email);
        user.setGmailId(gmailId);
        userService.addNewUser(user);
        model.put("title", TITLE);

        model.put("firstname", user.getFirstName());
        model.put("lastname", user.getLastName());
        model.put("username", user.getUsername());
        model.put("email", user.getEmail());

        return "profile";
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
