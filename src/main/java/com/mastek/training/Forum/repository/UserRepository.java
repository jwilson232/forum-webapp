package com.mastek.training.Forum.repository;

import com.mastek.training.Forum.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
}
