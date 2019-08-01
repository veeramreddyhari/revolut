package com.revolut.poc.service;

import com.revolut.poc.model.User;
import com.revolut.poc.repository.UserRepository;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public void addUser(User user) {
        try {
            userRepository.persistUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserByName(String name) {
        User user = null;
        try {
            user = userRepository.get(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public void updateUser(User user, Integer id) {
        try {
            userRepository.updateUser(user, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
