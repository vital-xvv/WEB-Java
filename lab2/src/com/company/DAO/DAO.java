package com.company.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    private static final String URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String USER = "root";
    private static final String PASSWORD = "";


    static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Successfully connected to a database");
        } catch (SQLException throwables) {
            System.out.println("Failed to connect to a database");
            throwables.printStackTrace();
        }
    }


}
