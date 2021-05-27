package com.example.uchef;

public class UserData {

    private String userName;
    private String order;
    private String datetime;
    private int userImage;

    public UserData(String userName, String order, String datetime, int userImage) {
        this.userName = userName;
        this.order = order;
        this.datetime = datetime;
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public String getOrder() {
        return order;
    }

    public String getDatetime() {
        return datetime;
    }

    public int getUserImage() {
        return userImage;
    }
}

