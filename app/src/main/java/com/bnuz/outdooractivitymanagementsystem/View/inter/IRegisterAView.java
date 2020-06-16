package com.bnuz.outdooractivitymanagementsystem.View.inter;

public interface IRegisterAView extends BaseView{

    //如果需要外部调用activity内的方法，现在下面定义

    <T> String getName();

    <T> String getPassword();

    <T> String getPassword_repeat();


}
