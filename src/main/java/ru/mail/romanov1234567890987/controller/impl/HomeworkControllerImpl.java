package ru.mail.romanov1234567890987.controller.impl;

import ru.mail.romanov1234567890987.controller.HomeworkController;
import ru.mail.romanov1234567890987.homeworkService.HomeworkService;
import ru.mail.romanov1234567890987.homeworkService.impl.HomeworkServiceImpl;
import ru.mail.romanov1234567890987.homeworkService.model.GroupDTO;
import ru.mail.romanov1234567890987.homeworkService.model.UserDTO;
import ru.mail.romanov1234567890987.repository.model.User;

import java.util.Random;

public class HomeworkControllerImpl implements HomeworkController {

    private HomeworkService userService = new HomeworkServiceImpl();
    private Random random = new Random();

    public static final String GROUP_NAME = "75050";

    @Override
    public void runFirstTask() {
        userService.dropTablesIfExists();
    }

    @Override
    public void runSecondTask() {
        userService.createTables();
    }

    @Override
    public void runThirdTask() {
        int groupCount = 3;
        for (int i = 0; i < groupCount; i++) {
            GroupDTO groupDTO = new GroupDTO();
            groupDTO.setGroupName(GROUP_NAME + i);
            userService.addGroup(groupDTO);
        }
    }

    @Override
    public void runFourthTask() {
        for (int i = 0; i < 30; i++) {
            UserDTO userDTO = new UserDTO();
            userDTO.setGroupName(GROUP_NAME + random.nextInt(6));
           
        }
    }
}
