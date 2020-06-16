package com.bnuz.outdooractivitymanagementsystem.bean;

import java.util.Date;

//活动节点信息（如：开始，结束，休息）
public class ActivityNode {
    private String nodeName;//节点名称
    private Integer activityId;//该节点所属的活动id
    private String type;//节点的类型
    private Date begin;//开始时间
    private Date end;//结束时间
    private String locate;//位置信息
    private String des;//节点描述

    public ActivityNode() {
    }

    public ActivityNode(String nodeName, Integer activityId, String type, Date begin, Date end, String locate, String des) {
        this.nodeName = nodeName;
        this.activityId = activityId;
        this.type = type;
        this.begin = begin;
        this.end = end;
        this.locate = locate;
        this.des = des;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
