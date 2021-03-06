package com.revolut.poc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

    private static JdbcConnection jdbc;

    private JdbcConnection() {
    }

    public static JdbcConnection getInstance() {
        if (jdbc == null) {
            jdbc = new JdbcConnection();
        }
        return jdbc;
    }

    static Connection getConnection() throws ClassNotFoundException, SQLException {

        Connection con = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://revolut.cuksm5goronx.us-east-1.rds.amazonaws.com:3306/revolut", "admin", "admin123!!!");
        return con;

    }
}
