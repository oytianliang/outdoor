package com.bnuz.outdooractivitymanagementsystem.bean;

//任务信息
public class Task {
    private Integer taskId;//任务id，自动生成
    private Integer activityId;//该任务所属的活动id
    private String taskName;//任务名称
    private String des;//任务描述

    public Task() {
    }

    public Task(Integer taskId, Integer activityId, String taskName, String des) {
        this.taskId = taskId;
        this.activityId = activityId;
        this.taskName = taskName;
        this.des = des;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
