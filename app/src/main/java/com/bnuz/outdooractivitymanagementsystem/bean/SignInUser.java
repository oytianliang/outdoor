package com.bnuz.outdooractivitymanagementsystem.bean;

import java.util.Date;

//已打卡的用户列表
public class SignInUser {
    private Integer signinId;//打卡的id
    private Integer userId;//已打卡的用户id
    private Date time;//打卡的时间
    private String locate;//打卡的地点

    public SignInUser() {
    }

    public SignInUser(Integer signinId, Integer userId, Date time, String locate) {
        this.signinId = signinId;
        this.userId = userId;
        this.time = time;
        this.locate = locate;
    }

    public Integer getSigninId() {
        return signinId;
    }

    public void setSigninId(Integer signinId) {
        this.signinId = signinId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }
}
