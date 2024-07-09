package com.learn.user.services;

import com.learn.user.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

    //update
    // delete
}
