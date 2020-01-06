package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.model.Thread;
import com.mastek.training.Forum.model.User;
import com.mastek.training.Forum.services.ThreadService;
import com.mastek.training.Forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.mastek.training.Forum.common.Constants.Common.*;
import static com.mastek.training.Forum.common.Constants.URIPaths.THREAD_ID;

@RequestMapping(THREADS)
@Controller
public class ThreadController {

    @Value("${app.thread.title}")
    private String TITLE = "";

    @Autowired
    private ThreadService threadService;

    @Autowired
    private UserService userService;

    @GetMapping(SEARCH)
    public List<Thread> searchThread(@RequestParam(required = false) String key, @RequestParam(required = false) String value) {
        return threadService.searchThreads(key, value);
    }

    @RequestMapping(SORT + RANK + ASC)
    public String sortThreadsByRankAsc(Map<String, Object> model) {
        model.put("title", TITLE);
        model.put("thread", threadService.sortThreadsByRankingAsc());
        return "threads";
    }

    @RequestMapping(SORT + RANK + DESC)
    public String sortThreadsByRankDesc(Map<String, Object> model) {
        model.put("title", TITLE);
        model.put("thread", threadService.sortThreadsByRankingDesc());
        return "threads";
    }

    @GetMapping
    public List<Thread> getAllThreads() { return threadService.sortByNewestThread(); }

    @PostMapping(CREATE)
    public String addNewThread(OAuth2Authentication oAuth2Authentication, @ModelAttribute("thread") Thread thread,
                               Map<String, Object> model) {

        threadService.addNewThread(thread, oAuth2Authentication);
        model.put("title", TITLE);
        return "threads";
    }

    @DeleteMapping(DELETE + THREAD_ID)
    public String deleteThread(@PathVariable("threadId") String threadId) { return threadService.deleteThread(threadId); }

    @RequestMapping(PAGE)
    public String thread(Map<String, Object> model, OAuth2Authentication oAuth2Authentication) {
        model.put("title", TITLE);
        model.put("loggedIn", userService.isUserLoggedIn(oAuth2Authentication));
        return "addthread";
    }

}