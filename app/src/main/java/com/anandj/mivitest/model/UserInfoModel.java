package com.anandj.mivitest.model;

import java.util.HashMap;

public class UserInfoModel {

    String tabName;
    String userInfo;

    public UserInfoModel(String tabName, String userInfo) {
        this.tabName = tabName;
        this.userInfo = userInfo;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
