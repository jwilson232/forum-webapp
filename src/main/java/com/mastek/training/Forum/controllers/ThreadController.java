package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.model.Thread;
import com.mastek.training.Forum.repository.ThreadRepository;
import com.mastek.training.Forum.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.mastek.training.Forum.model.Thread.Comparators.*;

@RestController
public class ThreadController {

    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private ThreadRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/thread")
    public String saveThread(@RequestBody Thread thread) {
        repository.save(thread);
        return "Added thread with id: " + thread.getId();
    }

//    @GetMapping("/threads")
//    public List<Thread> getThreads(@RequestParam int page) {
//        final Pageable pageableRequest = PageRequest.of(page, 2);
//        Query pagableQuery = new Query();
//        pagableQuery.with(pageableRequest);
//        List<Thread> threads = mongoTemplate.find(pagableQuery, Thread.class);
//        return threads;
//    }

    @GetMapping("/thread")
    public List<Thread> getCustomThread(@RequestParam(required = false) String key, @RequestParam(required = false) String value,
                                        @RequestParam(required = false) Integer page, @RequestParam(required = false) String sort) {
        List<Thread> threads;
        if (page == null) page = 0;
        Query customFilter = new Query();
        customFilter.addCriteria(Criteria.where(key).is(value));

        if (key == null || value == null) {
            threads = mongoTemplate.findAll(Thread.class);
        } else {
            threads = mongoTemplate.find(customFilter, Thread.class);
        }

        Comparator<Thread> threadComparator = TITLE;

        if (sort != null) {
            if (sort.equals("rank")) {
                threadComparator = RANKING;
            } else if (sort.equals("body")) {
                threadComparator = BODY;
            }
        }

        Collections.sort(threads, threadComparator);

        return threads;
    }

    @DeleteMapping("/thread/{id}")
    public String deleteThread(@PathVariable Integer id) {
        repository.deleteById(id);
        return "Deleted thread with id: " + id;
    }
}
