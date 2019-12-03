package com.mastek.training.Forum.services;

import com.mastek.training.Forum.model.Thread;
import com.mastek.training.Forum.repository.ThreadRepository;
import com.mastek.training.Forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
            thread.setDate(LocalDateTime.now());
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

        public List<Thread> sortThreadsByRankingAsc() {
            return getAllThreads().stream()
                                  .sorted(Comparator.comparingInt(Thread::getRanking))
                                  .collect(Collectors.toList());
        }

        public List<Thread> sortThreadsByRankingDesc() {
            return getAllThreads().stream()
                                  .sorted(Comparator.comparingInt(Thread::getRanking).reversed())
                                  .collect(Collectors.toList());
        }

        public List<Thread> sortByNewestThread() {
            return getAllThreads().stream()
                                  .sorted(Comparator.comparing(Thread::getDate).reversed())
                                  .collect(Collectors.toList());
        }

        public String deleteThread(String threadId) {
            Thread thread = mongoTemplate.findById(threadId, Thread.class);
            threadRepository.delete(thread);
            return "Thread: " + thread.getId() + " has been deleted.";
        }

        public List<Thread> getAllThreads() {
            return mongoTemplate.findAll(Thread.class);
        }

}
