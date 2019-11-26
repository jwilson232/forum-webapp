package com.mastek.training.Forum.repository;

import com.mastek.training.Forum.model.Thread;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ThreadRepository extends PagingAndSortingRepository<Thread, Integer> {
}
