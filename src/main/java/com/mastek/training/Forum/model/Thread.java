package com.mastek.training.Forum.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "thread")
public class Thread {

    @Transient
    public static final String SEQUENCE_NAME = "thread_sequence";

    @Id
    private String id;
    private String title;
    private String body;
    private LocalDateTime date;
    private int ranking;

    public Thread(String title, String body, int ranking) {
        this.title = title;
        this.body = body;
        this.date = java.time.LocalDateTime.now();
        this.ranking = ranking;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
