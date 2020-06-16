package com.bnuz.outdooractivitymanagementsystem.bean;

import java.io.Serializable;

//用户信息
public class User implements Serializable{
    //fields
    private Integer userId;//用户id，自动生成
    private String userName;//用户名，不可重复
    private String password;//用户密码
    private Integer permission;//用户权限，暂无用处
    private Integer sex;//性别
    private String city;//所在城市，长度较短 （已加长5倍，只存城市名，不要存地址）
    private String number;//用户手机
    private String interest;//附加，兴趣爱好，用于接收用户注册完后，选兴趣爱好
    private String head;//一个URL用于保存头像



    //constructor
    public User() { }

    public User(Integer userId, String userName, String password, Integer permission, Integer sex, String city, String number, String interest) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.permission = permission;
        this.sex = sex;
        this.city = city;
        this.number = number;
        this.interest = interest;

    }

    public User(User user){
        this.userId = user.userId;
        this.userName = user.userName;
        this.password = user.password;
        this.permission = user.permission;
        this.sex = user.sex;
        this.city = user.city;
        this.number = user.number;
        this.interest = user.interest;
        this.head = user.head;
    }

    public User(String userName, String number ,String city, Integer sex, String interest) {
        this.userName = userName;
        this.sex = sex;
        this.city = city;
        this.number = number;
        this.interest = interest;
    }

    //get&set
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getHead(){return head;}

    public void setHead(String head){this.head = head;}
}
