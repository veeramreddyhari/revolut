package com.revolut.poc.service;

import com.revolut.poc.model.User;
import com.revolut.poc.repository.UserRepository;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserRepository  userRepository = new UserRepository();
    @Test
    public void updateUser() throws Exception {
        User user = new User();
        user.setName("Test");
        user.setId(20);
        user.setDob(new Date(System.currentTimeMillis()));
        userRepository.updateUser(user ,20);
    }

}