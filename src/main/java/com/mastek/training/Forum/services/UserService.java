package com.mastek.training.Forum.services;

import com.mastek.training.Forum.model.User;
import com.mastek.training.Forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addNewUser(User user) {
        userRepository.save(user);
        return user;
    }
}
