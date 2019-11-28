package com.mastek.training.Forum.services;

import com.mastek.training.Forum.model.Thread;
import com.mastek.training.Forum.repository.ThreadRepository;
import com.mastek.training.Forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThreadService {

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

        public Thread addNewThread(Thread thread) {
            thread.setComments(new ArrayList<>());
            threadRepository.save(thread);
            return thread;
        }

        public List<Thread> searchThreads(String key, String value) {
            List<Thread> threads;
            Query customFilter = new Query();
            customFilter.addCriteria(Criteria.where(key).is(value));
            threads = (key == null || value == null) ? mongoTemplate.findAll(Thread.class) : mongoTemplate.find(customFilter, Thread.class);
            return threads;
        }

//        public List<Thread> filterThread(Integer pageSize, String sort, Integer page) {
//            Pageable sortByAsc = PageRequest.of(page, pageSize, Sort.by(sort));
//            Page<Thread> threads = threadRepository.findAll(sortByAsc);
//            return threads.getContent();
//        }

        public String deleteThread(String threadid) {
            Thread thread = mongoTemplate.findById(threadid, Thread.class);
            threadRepository.delete(thread);
            return "Thread: " + thread.getId() + " has been deleted.";
        }

        public List<Thread> getAllThreads() {
            return mongoTemplate.findAll(Thread.class);
        }

}
