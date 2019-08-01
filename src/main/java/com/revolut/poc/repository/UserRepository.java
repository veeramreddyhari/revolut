package com.revolut.poc.repository;

import com.revolut.poc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    private JdbcConnection jdbcConnection;
    private Connection connection = null;

    private PreparedStatement preparedStatement = null;

    public void persistUser(User user) {
        try {

            connection = jdbcConnection.getConnection();
            preparedStatement = connection.prepareStatement("insert into user(name,date_of_birth)values(?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, user.getDob());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
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
        try {

            connection = jdbcConnection.getConnection();
            preparedStatement = connection.prepareStatement(" update user set name=?,date_of_birth=? where id='" + id + "' ");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, user.getDob());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
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
