package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.inter.ICreateAModel;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpRespon;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpUtil;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.bean.Activity;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CreateAModelImpl implements ICreateAModel {
    @Override
    public void createactivity(final Activity activity, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/activity/add";
        String json = new Gson().toJson(activity);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        httpUtil.getDate(url, body, new HttpRespon<Activity>(Activity.class) {

            @Override
            public void onSuccess(Activity activity) {
                if(activity.getActivityId()>0){

                    callBack.onSuccess(activity);
                } else if(activity.getActivityId()==0)
                    callBack.onError("创建活动失败");
                else
                    callBack.onError("用户不存在");

            }
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误(创建活动)");
            }

        });
    }
}
