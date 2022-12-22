package com.DAO;

import com.models.Role;
import com.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAO extends DAO{

    public User getUserByLoginAndPassword(String login, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE login=? AND password=?");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            while(resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.convertRole(resultSet.getString("role")));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String login, String password, String role) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user (login, password, role) VALUES(?,?,?)");
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, role);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
