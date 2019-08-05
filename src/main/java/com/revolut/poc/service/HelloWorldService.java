package com.revolut.poc.service;

import com.revolut.poc.model.User;
import com.revolut.poc.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

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
        String message = null;
        try {
            if (name == null) {
                throw new WebApplicationException(Response.Status.NO_CONTENT);
            } else if (name != null && Pattern.matches("[a-zA-Z]+", name)) {
                user.setName(name);
                message = validateUser(user);
                oldUser = userService.getUserByName(name);

                if (oldUser != null) {
                    userService.updateUser(user, oldUser.getId());
                } else {
                    userService.addUser(user);
                }
            } else {
                message = name + " must contains only letters !";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok().entity(message).type("text/plain").build();
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
        Integer diff = null;
        Date current = new java.sql.Date(System.currentTimeMillis());

        if (DateUtil.isSameDay(current, user.getDob())) {
            message = "Hello, " + user.getName() + "!" + " Happy birthday !";
        } else {
            Long daysInDiff = fetchDays(current.toLocalDate(), user.getDob().toLocalDate());
            message = "Hello, " + user.getName() + "!" + " Your birthday is in " + daysInDiff + " days(s)";
        }
        return message;
    }

    private String validateUser(User user) {
        String message = null;
        Date current = new java.sql.Date(System.currentTimeMillis());
        if (user.getDob() != null) {
            if (!user.getDob().before(current)) {
                message = "Date of birth must be a date before the today date!";
            }
        }
        return message;
    }

    private Long fetchDays(LocalDate currentDate, LocalDate birthDay) {
        Long days = 0l;
        LocalDate nextBDay = birthDay.withYear(currentDate.getYear());

        if (nextBDay.isBefore(currentDate) || nextBDay.isEqual(currentDate)) {
            nextBDay = nextBDay.plusYears(1);
        }
        days = ChronoUnit.DAYS.between(currentDate, nextBDay);
        return days;
    }
}