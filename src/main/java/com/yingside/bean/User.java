package com.yingside.bean;

public class User {
    private Integer id;
    private String userTel;
    private String username;
    private String password;
    private String registrationTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userTel='" + userTel + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", registrationTime='" + registrationTime + '\'' +
                '}';
    }
}
