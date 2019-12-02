package com.mastek.training.Forum.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Document(collection = "thread")
@Data
@NoArgsConstructor
public class Thread {

    @Id
    private String id;
    private String title;
    private String body;
    private LocalDateTime date;
    private int ranking;
    private List<Comment> comments = new ArrayList<>();

    public Thread(int ranking, List<Comment> comments, String title, String body) {
        this.ranking = ranking;
        this.comments = comments;
        this.title = title;
        this.body = body;
        this.date = LocalDateTime.now();
    }

}