package com.bnuz.outdooractivitymanagementsystem.bean;

//活动用户列表
public class ActivityUser {
    private Integer activityId;//所属活动id
    private Integer userId;//参加的用户id
    private Integer authority;//该用户的权限，1为拥有者（发起者）,2为管理员，3为普通参加用户

    public ActivityUser() {
    }

    public ActivityUser(Integer activityId, Integer userId, Integer authority) {
        this.activityId = activityId;
        this.userId = userId;
        this.authority = authority;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }
}

