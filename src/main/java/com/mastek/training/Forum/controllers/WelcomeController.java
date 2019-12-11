package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.model.User;
import com.mastek.training.Forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static com.mastek.training.Forum.common.Constants.Common.WELCOME;

@Controller
public class WelcomeController {

    @Autowired
    UserService userService;

    @Value("${app.welcome.title}")
    private String TITLE = "";

    @RequestMapping(WELCOME)
    public String welcome(Map<String, Object> model, OAuth2Authentication oAuth2Authentication) {
        String welcome = "welcome";
        String gmailId = userService.googleDetails(oAuth2Authentication).get("id");
        User user = userService.customUserSearch("gmailId", gmailId);

        model.put("title", TITLE);
        model.put("message", "Hello " + userService.googleDetails(oAuth2Authentication).get("email"));

        if (user == null) {
            welcome = "welcomewarn";
        }

        return welcome;
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("title", TITLE);
        return "index";
    }
}
