package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.model.Comment;
import com.mastek.training.Forum.model.Thread;
import com.mastek.training.Forum.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static com.mastek.training.Forum.common.Constants.Common.*;

@RestController
@RequestMapping(COMMENTS)
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping
    public Thread addNewComment(@RequestBody Comment comment, @RequestParam String threadId) {
        return commentService.addNewComment(comment, threadId);
    }

    @DeleteMapping
    public Thread deleteComment(@RequestParam String threadId, @RequestParam int commentIndex) {
        return commentService.deleteComment(threadId, commentIndex);
    }

}
