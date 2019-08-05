package com.revolut.poc.service;

import com.revolut.poc.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class HelloWorldServiceTest {
    UserService userService = new UserService();

    @Test
    public void persistUser() throws Exception {
        User user = new User();
        user.setName("Test");
        user.setDob(new Date(System.currentTimeMillis()));
        userService.addUser(user);
    }

    @Test
    public void getUser() throws Exception {
        userService.getUserByName("Test");
    }

}