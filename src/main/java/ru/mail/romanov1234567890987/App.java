package ru.mail.romanov1234567890987;


import ru.mail.romanov1234567890987.controller.HomeworkController;
import ru.mail.romanov1234567890987.controller.impl.HomeworkControllerImpl;

public class App {


    public static void main(String[] args) {
        HomeworkController homeworkController = new HomeworkControllerImpl();
        //homeworkController.runFirstTask();
        //homeworkController.runSecondTask();
        //homeworkController.runThirdTask();
        homeworkController.runFourthTask();
    }
}
