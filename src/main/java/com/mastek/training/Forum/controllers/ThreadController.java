package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.model.Thread;
import com.mastek.training.Forum.repository.ThreadRepository;
import com.mastek.training.Forum.repository.UserRepository;
import com.mastek.training.Forum.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/threads")
@RestController
public class ThreadController {

    @Autowired
    private ThreadService threadService;

    @GetMapping("/search")
    public List<Thread> searchThread(@RequestParam(required = false) String key, @RequestParam(required = false) String value) {
        return threadService.searchThreads(key, value);
    }

    @GetMapping("/rankingAsc")
    public List<Thread> filterThreadByRankingAsc() { return threadService.sortThreadsByRatingAsc(); }

    @GetMapping("/rankingDesc")
    public List<Thread> filterThreadByRankingDesc() { return threadService.sortThreadsByRatingDesc(); }

    @GetMapping
    public List<Thread> getAllThreads() { return threadService.sortByNewestThread(); }

    @PostMapping("/create")
    public Thread addNewThread(@RequestBody Thread thread) {
        return threadService.addNewThread(thread);
    }

    @DeleteMapping("/delete/{threadId}")
    public String deleteThread(@PathVariable("threadId") String threadId) { return threadService.deleteThread(threadId); }

}