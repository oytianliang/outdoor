package com.bnuz.outdooractivitymanagementsystem.bean;

import java.util.Date;



//活动信息
public class Activity {
    private Integer activityId;//活动Id，自动生成
    private String activityTitle;//活动标题
    private String des;//活动描述
    private Date begin;//活动开始时间
    private Date end;//活动结束时间
    private String current;//活动当前
    private Integer owner;//活动发起者（创建者）
    private Date deadline;//报名结束日期
    private String locate;//活动位置
    private String activityShow;//一个URL，显示图片



    public Activity(Integer activityId, String activityTitle, String des, Date begin, Date end, String current, Integer owner, Date deadline, String locate) {
        this.activityId = activityId;
        this.activityTitle = activityTitle;
        this.des = des;
        this.begin = begin;
        this.end = end;
        this.current = current;
        this.owner = owner;
        this.deadline = deadline;
        this.locate = locate;
    }

    public Activity( Integer owner,String activityTitle, String des, Date begin, Date end,  Date deadline, String locate) {
        this.activityTitle = activityTitle;
        this.des = des;
        this.begin = begin;
        this.end = end;
        this.deadline = deadline;
        this.locate = locate;
        this.owner = owner;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public String getActivityShow(){return activityShow;}

    public void setActivityShow(String activityShow) {this.activityShow=activityShow;}
}
