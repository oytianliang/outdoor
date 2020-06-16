package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.bean.Activity;
import com.bnuz.outdooractivitymanagementsystem.bean.ActivityUser;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.IActivityUserAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpRespon;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpUtil;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ActivityUserAModelImpl implements IActivityUserAModel {
    @Override
    public void AttendActivity(ActivityUser activityUser,final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/ActivityUser/attend";
        String json = new Gson().toJson(activityUser);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        httpUtil.getDate(url, body, new HttpRespon<String>(String.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(String s) {
                switch (s) {
                    case "AttendSuccess":
                        callBack.onSuccess("参加成功");
                        break;
                    case "Owner":
                        callBack.onSuccess("该用户已经是创建者");
                        break;
                    case "Manager":
                        callBack.onSuccess("该用户已经是管理员");
                        break;
                    case "Attended":
                        callBack.onSuccess("该用户已参加");
                        break;
                    case "500错误":
                        callBack.onError("活动不存在或用户不存在");
                        break;
                    default:
                        callBack.onError(s);
                        break;
                }
            }


        });
    }


    @Override
    public void DeleteUserInActivity(int activityId, int userId,final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/ActivityUser/delete?activityId="+activityId+"&userId="+userId;
        httpUtil.getDate(url, null, new HttpRespon<String>(String.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(String s) {
                switch (s) {
                    case "Success":
                        callBack.onSuccess("成功");
                        break;
                    case "NoFind":
                        callBack.onError("找不到活动");
                        break;
                    case "Owner":
                        callBack.onError("无法删除拥有者");
                        break;
                    default:
                        callBack.onError(s);
                        break;
                }
            }


        });
    }

    @Override
    public void UpdateAuthority(ActivityUser activityUser,final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/ActivityUser/update";
        String json = new Gson().toJson(activityUser);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        httpUtil.getDate(url, body, new HttpRespon<String>(String.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(String s) {
                switch (s) {
                    case "ok":
                        callBack.onSuccess("更新成功");
                        break;
                    case "Owner":
                        callBack.onError("正在试图修改创建者");
                        break;
                    case "Cant":
                        callBack.onError("无法修改为创建者");
                        break;
                    case "NotExit":
                        callBack.onError("用户或活动不存在");
                        break;
                    default:
                        callBack.onError(s);
                        break;
                }
            }


        });
    }

    @Override
    public void getActivitysByUserId(int activityId,final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/ActivityUser/getAttendActivity?userId="+activityId;
        httpUtil.getDate(url, null, new HttpRespon<Activity[]>(Activity[].class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(Activity[] activities) {
                if(activities!=null){
                    callBack.onSuccess(activities);
                }
                else callBack.onError("获取失败");
            }


        });
    }
}
