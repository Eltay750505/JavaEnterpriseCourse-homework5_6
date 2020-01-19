package ru.mail.romanov1234567890987.homeworkService;

import ru.mail.romanov1234567890987.homeworkService.model.GroupDTO;
import ru.mail.romanov1234567890987.homeworkService.model.UserDTO;

public interface HomeworkService {

    void dropTablesIfExists();

    void createTables();

    GroupDTO addGroup(GroupDTO groupDTO);

    UserDTO addUser(UserDTO userDTO);
}
