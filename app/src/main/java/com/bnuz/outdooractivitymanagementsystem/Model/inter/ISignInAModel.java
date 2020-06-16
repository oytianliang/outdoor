package com.bnuz.outdooractivitymanagementsystem.Model.inter;

import com.bnuz.outdooractivitymanagementsystem.bean.SignIn;
import com.bnuz.outdooractivitymanagementsystem.bean.SignInUser;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;

public interface ISignInAModel {
    void createSign(SignIn signIn, CallBack callBack);//发起签到
    void deleteSign(SignIn signIn, CallBack callBack);//删除签到
    void updateSign(SignIn signIn, CallBack callBack);//更新签到
    void signIn(SignInUser signInUser, CallBack  callback);//签到
    void getAllSignByActivityID(int activityId, CallBack callBack);//获取活动的所有签到信息列表
    void getAllUserBySignID(int signId, CallBack callBack);//获取该活动ID已签到的所有用户列表
}
