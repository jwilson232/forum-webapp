package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.model.Comment;
import com.mastek.training.Forum.model.Thread;
import com.mastek.training.Forum.services.CommentService;
import com.mastek.training.Forum.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import static com.mastek.training.Forum.common.Constants.URIPaths.THREAD_ID;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static com.mastek.training.Forum.common.Constants.Common.*;

@Controller
@RequestMapping(COMMENTS)
public class CommentController {

    @Value("${app.thread.title}")
    private String TITLE = "";

    @Autowired
    CommentService commentService;

    @Autowired
    ThreadService threadService;

    @PostMapping(CREATE + THREAD_ID)
    public String addNewComment(OAuth2Authentication oAuth2Authentication, @ModelAttribute("comment") Comment comment,
                               Map<String, Object> model, @PathVariable("threadId") String threadId) {
        commentService.addNewComment(comment, threadId, oAuth2Authentication);
        Thread thread = threadService.searchForOneThread("id", threadId);
        List<Comment> comments = thread.getComments();
        model.put("thread", thread);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        model.put("date", thread.getDate().format(formatter));
        model.put("comments", comments);
        model.put("title", TITLE);
        model.put("username", thread.getUsername());
        model.put("body", thread.getBody());
        return "thread";
    }

    @DeleteMapping
    public Thread deleteComment(@RequestParam String threadId, @RequestParam int commentIndex) {
        return commentService.deleteComment(threadId, commentIndex);
    }

}
