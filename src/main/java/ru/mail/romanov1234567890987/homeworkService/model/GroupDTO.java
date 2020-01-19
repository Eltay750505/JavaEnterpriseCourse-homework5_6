package ru.mail.romanov1234567890987.homeworkService.model;

public class GroupDTO {
    private String groupName;
    private int usersCount;

    public GroupDTO() {
        usersCount = 0;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }
}
