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

    @GetMapping(SORT + RANK + ASC)
    public List<Thread> sortThreadsByRankAsc() { return threadService.sortThreadsByRankingAsc(); }

    @GetMapping(SORT + RANK + DESC)
    public List<Thread> sortThreadsByRankDesc() { return threadService.sortThreadsByRankingDesc(); }

    @GetMapping
    public List<Thread> getAllThreads() { return threadService.sortByNewestThread(); }

    @PostMapping(CREATE)
    public String addNewThread(OAuth2Authentication oAuth2Authentication, @ModelAttribute("thread") Thread thread,
                               Map<String, Object> model) {
        String gmailId = userService.googleDetails(oAuth2Authentication).get("id");
        thread.setUserGmailId(gmailId);
        threadService.addNewThread(thread);
        model.put("title", TITLE);
        return "threads";
    }

    @DeleteMapping(DELETE + THREAD_ID)
    public String deleteThread(@PathVariable("threadId") String threadId) { return threadService.deleteThread(threadId); }

    @RequestMapping(PAGE)
    public String thread(Map<String, Object> model, OAuth2Authentication oAuth2Authentication) {
        if (userService.getUserByGmailId(oAuth2Authentication) == null) {
            model.put("title", TITLE);
            return "threadswarn";
        } else {
            model.put("title", TITLE);
            return "threads";
        }

    }

}