package com.mastek.training.Forum.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequence")
@Getter
@Setter
public class DatabaseSequence {

    @Transient
    public static final String SEQUENCE_NAME = "thread_sequence";

    @Id
    private String id;

    private int seq;

}
