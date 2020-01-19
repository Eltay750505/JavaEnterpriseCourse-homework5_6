package ru.mail.romanov1234567890987.repository.impl;

import ru.mail.romanov1234567890987.homeworkService.model.GroupDTO;
import ru.mail.romanov1234567890987.repository.UserRepository;
import ru.mail.romanov1234567890987.repository.model.User;
import ru.mail.romanov1234567890987.repository.model.UserGroup;

import java.io.Serializable;
import java.sql.*;
import java.util.List;

public class UserRepositoryImpl extends GeneralRepositoryImpl<User> implements UserRepository {

    private static UserRepository instance;

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public User add(Connection connection, User user) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO user(name, age, is_active, user_group_id, age) VALUES (?,?,?,(SELECT id FROM " +
                                "user_group WHERE name = ?),?)",
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.getActive());
            statement.setString(3, user.getUserGroupName());
            statement.setInt(3, user.getAge());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating person failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return user;
        }
    }

    @Override
    public User get(Connection connection, Serializable id) throws SQLException {
        return null;
    }

    @Override
    public void update(Connection connection, User user) throws SQLException {

    }

    @Override
    public int delete(Connection connection, Serializable id) throws SQLException {
        return 0;
    }

    @Override
    public List<User> findAll(Connection connection) throws SQLException {
        return null;
    }


    @Override
    public int createTableUser(Connection connection) throws SQLException {
        int affectedRows;
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "CREATE TABLE user(\n" +
                                "id  INT PRIMARY KEY AUTO_INCREMENT,\n" +
                                "user_group_id INT, \n" +
                                "user_name VARCHAR(40) NOT NULL,\n" +
                                "password VARCHAR(40) NOT NULL,\n" +
                                "is_active BOOLEAN NOT NULL DEFAULT TRUE,\n" +
                                "FOREIGN KEY (user_group_id) REFERENCES user_group(id),\n" +
                                "age INT(40) NOT NULL\n" +
                                ");"
                )
        ) {
            affectedRows = statement.executeUpdate();
            return affectedRows;
        }
    }

    @Override
    public int createTableUserGroup(Connection connection) throws SQLException {
        int affectedRows;
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "CREATE TABLE user_group(\n" +
                                "id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                                "name VARCHAR(40) NOT NULL\n" +
                                ");"
                )
        ) {
            affectedRows = statement.executeUpdate();
            return affectedRows;
        }
    }

    @Override
    public int createTableUserInformation(Connection connection) throws SQLException {
        int affectedRows;
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "CREATE TABLE user_information(\n" +
                                "id  INT PRIMARY KEY AUTO_INCREMENT,\n" +
                                "user_id INT,\n" +
                                "adress VARCHAR(40) NOT NULL,\n" +
                                "telephone VARCHAR(40) NOT NULL,\n" +
                                "FOREIGN KEY (user_id) REFERENCES user(id)\n" +
                                ");"
                )
        ) {
            affectedRows = statement.executeUpdate();
            return affectedRows;
        }
    }

    @Override
    public void deleteTablesIfExists(Connection connection) throws SQLException {
        try(
                Statement statement = connection.createStatement();
                )
        {
             statement.executeUpdate("DROP TABLE IF EXISTS user");
             statement.executeUpdate("DROP TABLE IF EXISTS user_group");
             statement.executeUpdate("DROP TABLE IF EXISTS user_information");
        }
    }

    @Override
    public UserGroup addGroup(Connection connection, UserGroup userGroup) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO user_group(name) VALUES (?)",
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            statement.setString(1, userGroup.getName());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating person failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userGroup.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return userGroup;
        }
    }
}
