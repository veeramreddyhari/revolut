package com.revolut.poc.service;

import com.revolut.poc.model.User;
import com.revolut.poc.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

@Path("/user")
public class HelloWorldService {

    private static final Logger logger = LogManager.getLogger(HelloWorldService.class.getName());

    private UserService userService = new UserService();

    @PUT
    @Path("/add/{name}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response persistUser(@PathParam("name") String name, User user) {
        User oldUser = null;
        try {
            if (user.getName() == null) {
                throw new WebApplicationException(Response.Status.NO_CONTENT);
            }
            oldUser = userService.getUserByName(name);

            if (oldUser != null) {
                userService.updateUser(user, oldUser.getId());
            } else {
                userService.addUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok().entity(user).build();
    }

    @GET
    @Path("/get/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("name") String name) {
        logger.info(String.format("getUser behaviour started"));
        User user = null;
        String message = null;
        try {
            if (name == null) {
                throw new WebApplicationException(Response.Status.NO_CONTENT);
            }
            user = userService.getUserByName(name);
            if (user == null) {
                throw new WebApplicationException(Response.Status.NO_CONTENT);
            }
            message = processUser(user);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }
        logger.info(String.format("getUser behaviour completed"));
        return Response.ok().entity(message).type("text/plain").build();
    }

    private String processUser(User user) {

        String message = null;
        long diffInMillies = 0l;
        long diff = 0l;
        Date current = new java.sql.Date(System.currentTimeMillis());

        if (DateUtil.isSameDay(current, user.getDob())) {
            message = "Hello, " + user.getName() + "!" + " Happy birthday !";
        } else {
            diffInMillies = Math.abs(current.getTime() - user.getDob().getTime());
            diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            message = "Hello, " + user.getName() + "!" + " Your birthday is in " + diff + " days(s)";
        }
        return message;
    }
}