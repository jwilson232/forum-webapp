package com.mastek.training.Forum.services;

import com.mastek.training.Forum.model.Thread;
import com.mastek.training.Forum.model.User;
import com.mastek.training.Forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> getAllUsers() { return mongoTemplate.findAll(User.class); }

    public User addNewUser(User user) {
        userRepository.save(user);
        return user;
    }

    public String deleteUser(String userId) {
        User user = mongoTemplate.findById(userId, User.class);
        userRepository.delete(user);
        return "User " + user.getUsername() + " has been deleted";
    }

}
