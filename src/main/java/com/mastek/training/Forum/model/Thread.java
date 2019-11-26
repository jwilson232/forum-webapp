package com.mastek.training.Forum.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Document(collection = "thread")
public class Thread implements Comparable<Thread>{

    @Transient
    public static final String SEQUENCE_NAME = "thread_sequence";


    private String title;
    private String body;
    private LocalDateTime date;
    private int ranking;
    private List<Comment> comments;

    public Thread(int ranking, List<Comment> comments, String title, String body) {
        this.ranking = ranking;
        this.comments = comments;
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public int compareTo(Thread o) {
        return 0;
    }
}
