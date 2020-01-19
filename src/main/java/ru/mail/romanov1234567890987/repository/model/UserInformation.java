package ru.mail.romanov1234567890987.repository.model;

public class UserInformation {
    private int id;
    private int userId;
    private String adress;
    private String telephone;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getAdress() {
        return adress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    private UserInformation(Builder builder) {
        id = builder.id;
        userId = builder.userId;
        adress = builder.adress;
        telephone = builder.telephone;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private int userId;
        private String adress;
        private String telephone;

        private Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder userId(int val) {
            userId = val;
            return this;
        }

        public Builder adress(String val) {
            adress = val;
            return this;
        }

        public Builder telephone(String val) {
            telephone = val;
            return this;
        }

        public UserInformation build() {
            return new UserInformation(this);
        }
    }
}
