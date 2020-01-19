package ru.mail.romanov1234567890987.homeworkService.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mail.romanov1234567890987.homeworkService.HomeworkService;
import ru.mail.romanov1234567890987.homeworkService.model.GroupDTO;
import ru.mail.romanov1234567890987.homeworkService.model.UserDTO;
import ru.mail.romanov1234567890987.repository.ConnectionRepository;
import ru.mail.romanov1234567890987.repository.UserRepository;
import ru.mail.romanov1234567890987.repository.impl.ConnectionRepositoryImpl;
import ru.mail.romanov1234567890987.repository.impl.UserRepositoryImpl;
import ru.mail.romanov1234567890987.repository.model.User;
import ru.mail.romanov1234567890987.repository.model.UserGroup;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

public class HomeworkServiceImpl implements HomeworkService {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final int ADULT_AGE_VALUE = 18;
    private static final String ADULT_VALUE = "adult";
    private static final String GROUP_NAME = "75050";
    private Random random = new Random();

    private ConnectionRepository connectionRepository = ConnectionRepositoryImpl.getInstance();
    private UserRepository userRepository = UserRepositoryImpl.getInstance();

    @Override
    public void dropTablesIfExists() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                userRepository.deleteTablesIfExists(connection);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            } catch (NullPointerException e){
                connection.rollback();
                logger.error(e.getMessage(),e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void createTables() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                userRepository.createTableUserGroup(connection);
                userRepository.createTableUser(connection);
                userRepository.createTableUserInformation(connection);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            } catch (NullPointerException e){
                connection.rollback();
                logger.error(e.getMessage(),e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public GroupDTO addGroup(GroupDTO groupDTO) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                UserGroup userGroup = convertDTOToPerson(groupDTO);
                userGroup = userRepository.addGroup(connection, userGroup);
                GroupDTO userGroupToDTO = convertUserGroupToDTO(userGroup);
                connection.commit();
                return userGroupToDTO;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            int maxGroup = 6;
            try {
                userDTO.setGroupName(GROUP_NAME + random.nextInt(maxGroup));
                User user = convertUserDTOToPerson(userDTO);
                user = userRepository.add(connection, user);
                UserDTO userToDTO = convertUserToDTO(user);
                connection.commit();
                return userToDTO;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private UserDTO convertUserToDTO(User user) {
        UserDTO personDTO = new UserDTO();
        personDTO.setUserName(user.getUserName());
        personDTO.setAge(user.getAge());
        personDTO.setPassword(user.getPassword());
        applyCondition(user, personDTO);
        return personDTO;
    }

    private User convertUserDTOToPerson(UserDTO userDTO) {
        return User.newBuilder()
                .userName(userDTO.getUserName())
                .age(userDTO.getAge())
                .userGroupName(userDTO.getGroupName())
                .password(userDTO.getPassword())
                .build();
    }

    private GroupDTO convertUserGroupToDTO(UserGroup userGroup) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupName(userGroup.getName());
        return groupDTO;
    }

    private void applyCondition(User user, UserDTO userDTO) {
        if (user.getActive()) {
            if (user.getAge() >= ADULT_AGE_VALUE) {
                userDTO.setType(ADULT_VALUE);
            } else {
               userDTO.setType("no " + ADULT_VALUE);
            }
        }
    }

    private UserGroup convertDTOToPerson(GroupDTO groupDTO) {
        UserGroup userGroup = new UserGroup();
        userGroup.setName(groupDTO.getGroupName());
        return userGroup;
    }
}
