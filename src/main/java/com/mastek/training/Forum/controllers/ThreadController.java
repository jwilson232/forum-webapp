package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.model.Thread;
import com.mastek.training.Forum.model.User;
import com.mastek.training.Forum.repository.ThreadRepository;
import com.mastek.training.Forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ThreadController {

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    // userId will be gathered automatically from user who is logged in
    @PostMapping("/thread")
    public User userAddNewThread(@RequestBody Thread thread, @RequestParam String userId) {
        User user = mongoTemplate.findById(userId, User.class);
        List<Thread> threads = new ArrayList<>();
        if (user.getThreads() == null) {
            threads.add(thread);
        } else {
            for (Thread x : user.getThreads()) {
                threads.add(x);
            }
        }
        user.setThreads(threads);
        userRepository.save(user);
        return user;
    }

    @GetMapping("/threadSearch")
    public List<Thread> searchThread(@RequestParam(required = false) String key, @RequestParam(required = false) String value) {
        List<Thread> threads;
        Query customFilter = new Query();
        customFilter.addCriteria(Criteria.where(key).is(value));
        threads = (key == null || value == null) ? mongoTemplate.findAll(Thread.class) : mongoTemplate.find(customFilter, Thread.class);
        return threads;
    }

    @GetMapping("/threadFilter")
    public List<Thread> filterThread(@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sort, @RequestParam(required = false) Integer page) {
        Pageable sortByAsc = PageRequest.of(page, pageSize, Sort.by(sort));
        Page<Thread> threads = threadRepository.findAll(sortByAsc);
        return threads.getContent();
    }

    @DeleteMapping("/thread/{id}")
    public String deleteThread(@PathVariable Integer id) {
        threadRepository.deleteById(id);
        return "Deleted thread with id: " + id;
    }
}
