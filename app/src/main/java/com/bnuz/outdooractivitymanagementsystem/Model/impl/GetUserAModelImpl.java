package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.bean.User;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.IGetUserAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpRespon;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpUtil;


public class GetUserAModelImpl implements IGetUserAModel{
    @Override
    public void getUserById(int userId,final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/user/getById?userId="+userId;

        httpUtil.getDate(url, null, new HttpRespon<User>(User.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }


            @Override
            public void onSuccess(User user) {
                if(user!=null){
                    callBack.onSuccess(user);
                }
                else callBack.onError("请求错误");
            }


        });
    }

    @Override
    public void getUserByName(String name,final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/user/getByName?name="+name;

        httpUtil.getDate(url, null, new HttpRespon<User>(User.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }


            @Override
            public void onSuccess(User user) {
                if(user!=null){
                    callBack.onSuccess(user);
                }
                else callBack.onError("请求错误");
            }


        });
    }
}