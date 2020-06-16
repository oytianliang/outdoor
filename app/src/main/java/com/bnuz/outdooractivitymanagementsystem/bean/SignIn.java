package com.bnuz.outdooractivitymanagementsystem.bean;

import java.util.Date;

//打卡信息
public class SignIn {

    private Integer signInId;//打卡id
    private Integer activityId;//打卡所属的活动id
    private Date begin;//开始时间
    private Date end;//结束时间
    private String title;//标题
    private Integer type;//打卡类型，字符串，由前端决定

    public SignIn() {
    }

    public SignIn(Integer signInId, Integer activityId, Date begin, Date end, String title, Integer type) {
        this.signInId = signInId;
        this.activityId = activityId;
        this.begin = begin;
        this.end = end;
        this.title = title;
        this.type = type;
    }

    public Integer getSignInId() {
        return signInId;
    }

    public void setSignInId(Integer signInId) {
        this.signInId = signInId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
