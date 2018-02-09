package com.iths.manisedighi.databasetodo;

/**
 * Created by manisedighi on 09/02/2018.
 */

public class UserInfo {

    private int userId;
    private String userName;

    public UserInfo(int userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }

    public UserInfo(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
