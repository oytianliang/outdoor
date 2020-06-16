package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.inter.IManagement2AModel;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpRespon;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpUtil;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;


import com.bnuz.outdooractivitymanagementsystem.bean.Activity;
import com.bnuz.outdooractivitymanagementsystem.bean.User;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
public class Management2AModelImpl implements IManagement2AModel {
    @Override
    public void getUserByACTId(final int activityId, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/ActivityUser/getAttendUser?activityId="+activityId;
        String json = new Gson().toJson(activityId);
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
    @Override
    public void getACTByACTID(int activityId, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/activity/getById?activityId="+activityId;
        String json = new Gson().toJson(activityId);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        httpUtil.getDate(url, body, new HttpRespon<Activity>(Activity.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }


            @Override
            public void onSuccess(Activity activity) {
                if(activity!=null){
                    callBack.onSuccess(activity);
                }
                else callBack.onError("没有活动");
            }


        });
    }

}
