package com.bnuz.outdooractivitymanagementsystem.bean;

public class ActivityListView {//活动在界面显示

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    private String name;
    private String describe;
    private int imageId;//图片ID

    public ActivityListView(String name, String describe,int imageId) {
        this.name = name;
        this.describe = describe;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
