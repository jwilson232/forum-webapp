package com.mastek.training.Forum.repository;

import com.mastek.training.Forum.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {
}
