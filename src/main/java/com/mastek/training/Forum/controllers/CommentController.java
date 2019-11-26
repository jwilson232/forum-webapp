package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.model.Comment;
import com.mastek.training.Forum.model.Thread;
import com.mastek.training.Forum.model.User;
import com.mastek.training.Forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/comment")
    public Thread addNewComment(@RequestBody Comment comment, @RequestParam String userId, @RequestParam int threadIndex) {
        User user = mongoTemplate.findById(userId, User.class);
        Thread thread = user.getThreads().get(threadIndex);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        thread.setComments(comments);
        return thread;
    }
}
