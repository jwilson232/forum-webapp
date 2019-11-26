package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.model.Thread;
import com.mastek.training.Forum.repository.ThreadRepository;
import com.mastek.training.Forum.repository.UserRepository;
import com.mastek.training.Forum.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ThreadController {

    @Autowired
    private ThreadService threadService;

    @GetMapping("/threadSearch")
    public List<Thread> searchThread(@RequestParam(required = false) String key, @RequestParam(required = false) String value) {
        return threadService.searchThreads(key, value);
    }

    @GetMapping("/threadFilter")
    public List<Thread> filterThread(@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sort, @RequestParam(required = false) Integer page) {
        return threadService.filterThread(pageSize, sort, page);
    }

    @GetMapping("/threads")
    public List<Thread> getAllThreads() {
        return threadService.getAllThreads();
    }

    @PostMapping("/thread")
    public Thread addNewThread(@RequestBody Thread thread) {
        return threadService.addNewThread(thread);
    }

    @DeleteMapping("/thread/{threadid}")
    public String deleteThread(@PathVariable Integer threadid) {
        return threadService.deleteThread(threadid);
    }




}