package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.inter.IPersoninfoAModel;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpRespon;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpUtil;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.bean.User;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PersoninfoAModelImpl implements IPersoninfoAModel {
    @Override
    public void update(User user, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/user/update";
        String json = new Gson().toJson(user);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        httpUtil.getDate(url, body, new HttpRespon<String>(String.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(String s) {
                if (s.equals("保存成功"))
                    callBack.onSuccess(s);
                else
                    callBack.onError(s);
            }


        });
    }

}
