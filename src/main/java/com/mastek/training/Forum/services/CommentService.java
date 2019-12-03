package com.mastek.training.Forum.services;

import com.mastek.training.Forum.model.Comment;
import com.mastek.training.Forum.model.Thread;
import com.mastek.training.Forum.repository.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ThreadRepository threadRepository;

    public Thread addNewComment (Comment comment, String threadid) {
        Thread thread = mongoTemplate.findById(threadid, Thread.class);
        List<Comment> comments = thread.getComments();
        comments.add(comment);
        thread.setComments(comments);
        threadRepository.save(thread);
        return thread;
    }

    public Thread deleteComment (String threadId, int commentIndex) {
        Thread thread = mongoTemplate.findById(threadId, Thread.class);
        List<Comment> comments = thread.getComments();
        comments.remove(commentIndex);
        thread.setComments(comments);
        threadRepository.save(thread);
        return thread;
    }
}
