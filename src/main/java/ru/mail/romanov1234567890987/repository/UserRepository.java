package ru.mail.romanov1234567890987.repository;

import ru.mail.romanov1234567890987.homeworkService.model.GroupDTO;
import ru.mail.romanov1234567890987.repository.model.User;
import ru.mail.romanov1234567890987.repository.model.UserGroup;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserRepository extends GeneralRepository<User> {

    UserGroup addGroup(Connection connection, UserGroup userGroup) throws SQLException;

    int createTableUser(Connection connection) throws SQLException;

    int createTableUserGroup(Connection connection) throws SQLException;

    int createTableUserInformation(Connection connection) throws SQLException;

    void deleteTablesIfExists(Connection connection) throws SQLException;

}
