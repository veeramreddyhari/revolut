package com.revolut.poc.service;

import com.revolut.poc.model.User;
import com.revolut.poc.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class.getName());

    private UserRepository userRepository = new UserRepository();

    public void addUser(User user) {
        logger.info(String.format("addUser behaviour started",user));
        try {
            userRepository.persistUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("Exception raised in addUser behaviour ",e.getMessage()));
        }
        logger.info(String.format("addUser behaviour completed",user));
    }

    public User getUserByName(String name) {
        logger.info(String.format("getUserByName behaviour started",name));
        User user = null;
        try {
            user = userRepository.get(name);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("Exception raised in getUserByName behaviour ",e.getMessage()));
        }
        logger.info(String.format("getUserByName behaviour completed",name));
        return user;
    }

    public void updateUser(User user, Integer id) {
        logger.info(String.format("updateUser behaviour started",user));
        try {
            userRepository.updateUser(user, id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("Exception raised in updateUser behaviour ",e.getMessage()));
        }
        logger.info(String.format("updateUser behaviour completed",user));
    }
}
