package com.mastek.training.Forum.controllers;

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
        model.put("title", TITLE);
        model.put("message", "Hello " + userService.googleDetails(oAuth2Authentication).get("email"));
        model.put("bottom", "This is a simple forum webapp that uses MongoDB and Google Oauth2 authentication");
        return "welcome";
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("title", TITLE);
        return "index";
    }
}
