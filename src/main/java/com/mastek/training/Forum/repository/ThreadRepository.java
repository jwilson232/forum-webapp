package com.mastek.training.Forum.repository;

import com.mastek.training.Forum.model.Thread;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ThreadRepository extends MongoRepository<Thread, Integer> {

}
