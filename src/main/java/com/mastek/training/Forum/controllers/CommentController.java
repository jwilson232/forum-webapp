package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.model.Comment;
import com.mastek.training.Forum.model.Thread;
import com.mastek.training.Forum.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/comment")
    public Thread addNewComment(@RequestBody Comment comment, @RequestParam String threadid) {
        return commentService.addNewComment(comment, threadid);
    }

    @DeleteMapping("/comment")
    public Thread deleteComment(@RequestParam String threadid, @RequestParam int commentIndex) {
        return commentService.deleteComment(threadid, commentIndex);
    }

}
