package com.revolut.poc.repository;

import com.revolut.poc.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    private static final Logger logger = LogManager.getLogger(UserRepository.class.getName());

    private JdbcConnection jdbcConnection;
    private Connection connection = null;

    private PreparedStatement preparedStatement = null;

    public void persistUser(User user) {
        logger.info(String.format("persistUser behaviour Started"));
        try {

            connection = jdbcConnection.getConnection();
            preparedStatement = connection.prepareStatement("insert into user(name,date_of_birth)values(?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, user.getDob());
            preparedStatement.executeUpdate();
            logger.info(String.format("persistUser behaviour completed"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("Exception raised in persistUser Behaviour Started",e.getMessage()));
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public User get(String name) {

        logger.info(String.format("get behaviour Started"));
        ResultSet resultSet = null;
        User user = null;
        try {

            connection = jdbcConnection.getConnection();
            preparedStatement = connection.prepareStatement("select * from user where name=?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt(1));
                    user.setName(resultSet.getString(2));
                    user.setDob(resultSet.getDate(3));
                }
            }
            logger.info(String.format("persistUser behaviour completed"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("Exception raised in get behaviour ",e.getMessage()));
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return user;
    }

    public void updateUser(User user, Integer id) {
        logger.info(String.format("updateUser behaviour Started",user));
        try {

            connection = jdbcConnection.getConnection();
            preparedStatement = connection.prepareStatement(" update user set name=?,date_of_birth=? where id='" + id + "' ");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, user.getDob());
            preparedStatement.executeUpdate();
            logger.info(String.format("updateUser behaviour completed",user));

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("Exception raised in updateUser behaviour ",e.getMessage()));
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
