package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.inter.IRegisterAModel;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpRespon;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpUtil;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.bean.User;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class RegisterAModelImpl implements IRegisterAModel {
    @Override
    public void register(final User user, final CallBack callBack) {

            HttpUtil httpUtil = HttpUtil.getInstance();
            String url = "http://101.37.202.16:8080/back-spring/user/signUp";
            String json = new Gson().toJson(user);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
            httpUtil.getDate(url, body, new HttpRespon<User>(User.class) {


                @Override
                public void onSuccess(User re) {
                    if(re.getUserId()==0){
                        callBack.onError("用户名已存在");
                    }else if(re.getUserId()==null) {
                        callBack.onError("服务器没给ID");
                    }else
                        callBack.onSuccess(re);
                }

                @Override
                public void onError(String msg) {
                    callBack.onError("服务器错误");
                }
            });


    }
}
