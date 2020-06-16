package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.inter.IPersonalAModel;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpRespon;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpUtil;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;


import com.bnuz.outdooractivitymanagementsystem.bean.User;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
public class PersonalAModelImpl implements IPersonalAModel {
    @Override
    public void getUser(final int userId, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/user/getById?userId="+userId;
        String json = new Gson().toJson(userId);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        httpUtil.getDate(url, body, new HttpRespon<User[]>(User[].class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }


            @Override
            public void onSuccess(User[] users) {
                if(users!=null){
                    callBack.onSuccess(users);
                }
                else callBack.onError("没有活动");
            }


        });
    }

}
