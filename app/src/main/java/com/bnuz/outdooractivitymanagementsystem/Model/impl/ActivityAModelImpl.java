package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.bean.Activity;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.IActivityAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpRespon;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpUtil;

public class ActivityAModelImpl implements IActivityAModel {
    @Override
    public void getAll(int page,final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/activity/getAll?page="+page;
        httpUtil.getDate(url, null, new HttpRespon<Activity[]>(Activity[].class) {
            @Override
            public void onError(String str) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(Activity[] activities) {
                if(activities!=null)
                    callBack.onSuccess(activities);
                else
                    callBack.onError("null");
            }
        });
    }

    @Override
    public void getCount(final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/activity/getCount";
        httpUtil.getDate(url, null, new HttpRespon<Integer>(Integer.class) {
            @Override
            public void onError(String str) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(Integer count) {
                if(count!=null)
                    callBack.onSuccess(count);
                else
                    callBack.onError("null");
            }
        });
    }

    @Override
    public void delete(int activityId, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/activity/delete?activityId="+activityId;
        httpUtil.getDate(url, null, new HttpRespon<String>(String.class) {
            @Override
            public void onError(String str) {
                callBack.onError("服务器错误");
            }



            @Override
            public void onSuccess(String s) {
                if(s.equals("DeleteSuccess"))
                    callBack.onSuccess("删除成功");
                else
                    callBack.onError("删除失败");
            }
        });
    }
}
