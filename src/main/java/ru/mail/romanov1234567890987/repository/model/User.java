package ru.mail.romanov1234567890987.repository.model;

public class User {
    private int id;
    private String userName;
    private String password;
    private Boolean isActive;
    private int userGroupId;
    private int age;
    private String userGroupName;

    private User(Builder builder) {
        setId(builder.id);
        setUserName(builder.userName);
        setPassword(builder.password);
        isActive = builder.isActive;
        setUserGroupId(builder.userGroupId);
        setAge(builder.age);
        setUserGroupName(builder.userGroupName);
    }

    public int getId() {
        return id;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public int getUserGroupId() {
        return userGroupId;
    }

    public int getAge() {
        return age;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private String userName;
        private String password;
        private Boolean isActive;
        private Boolean is_active;
        private int userGroupId;
        private int age;
        private String userGroupName;

        private Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder isActive(Boolean val) {
            isActive = val;
            return this;
        }

        public Builder is_active(Boolean val) {
            is_active = val;
            return this;
        }

        public Builder userGroupId(int val) {
            userGroupId = val;
            return this;
        }

        public Builder age(int val) {
            age = val;
            return this;
        }

        public Builder userGroupName(String val) {
            userGroupName = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
