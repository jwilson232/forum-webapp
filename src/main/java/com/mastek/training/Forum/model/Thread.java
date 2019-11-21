package com.mastek.training.Forum.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Comparator;

@Document(collection = "thread")
public class Thread implements Comparable<Thread>{

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

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public int compareTo(Thread o) {
        return Comparators.TITLE.compare(this, o);
    }

    public static class Comparators {
        public static final Comparator<Thread> TITLE = (o1, o2) -> o1.title.compareTo(o2.title);
        public static final Comparator<Thread> BODY = (o1, o2) -> o1.body.compareTo(o2.body);
        public static Comparator<Thread> RANKING = (o1, o2) -> o1.ranking - o2.ranking;
   }
}
