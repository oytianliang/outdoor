package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.bean.SignIn;
import com.bnuz.outdooractivitymanagementsystem.bean.User;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.ISignInAModel;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpRespon;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpUtil;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.bean.SignInUser;

import com.google.gson.Gson;


import okhttp3.MediaType;
import okhttp3.RequestBody;
public class SignInAModelImpl implements ISignInAModel {
    @Override
    public void createSign(SignIn signIn, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/SignIn/add";
        String json = new Gson().toJson(signIn);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        httpUtil.getDate(url, body, new HttpRespon<SignIn>(SignIn.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(SignIn signIn) {
                if(signIn.getSignInId()>0)
                    callBack.onSuccess(signIn);
                else if(signIn.getSignInId() ==-1)
                    callBack.onError("活动不存在");
                else
                    callBack.onError("发起签到失败");//不存在
            }


        });
    }

    @Override
    public void deleteSign(SignIn signIn, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/SignIn/delete";
        String json = new Gson().toJson(signIn);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        httpUtil.getDate(url, body, new HttpRespon<String>(String.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(String s) {
                if(s.equals("ok"))
                    callBack.onSuccess("删除成功");
                else if(s.equals("SignInNotFind"))
                    callBack.onSuccess("找不到该签到");
                else
                    callBack.onError(s);
            }


        });
    }

    @Override
    public void updateSign(SignIn signIn, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/SignIn/update";
        String json = new Gson().toJson(signIn);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        httpUtil.getDate(url, body, new HttpRespon<String>(String.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(String s) {
                if(s.equals("ok"))
                    callBack.onSuccess("删除成功");
                else if(s.equals("SignInNotFind"))
                    callBack.onSuccess("找不到该签到");
                else
                    callBack.onError(s);
            }


        });
    }

    @Override
    public void signIn(SignInUser signInUser, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/SignInUser/add";
        String json = new Gson().toJson(signInUser);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        httpUtil.getDate(url, body, new HttpRespon<String>(String.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(String s) {
                if(s.equals("签到成功"))
                    callBack.onSuccess(s);
                else if(s.equals("该用户已签到"))
                    callBack.onSuccess(s);
                else
                    callBack.onError(s);
            }


        });
    }

    @Override
    public void getAllSignByActivityID(int activityId, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/SignIn/getByActivityID?activityId="+activityId;
         httpUtil.getDate(url, null, new HttpRespon<SignIn[]>(SignIn[].class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(SignIn[] signIns) {
                if(signIns!=null)
                    callBack.onSuccess(signIns);
                else
                    callBack.onError("获取签到信息失败");
            }


        });
    }

    @Override
    public void getAllUserBySignID(int signId, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/SignInUser/getAllBySignInId?signInId="+signId;
        httpUtil.getDate(url, null, new HttpRespon<User[]>(User[].class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(User[] users) {
                if(users!=null)
                    callBack.onSuccess(users);
                else
                    callBack.onError("获取签到信息失败");
            }


        });
    }
}
