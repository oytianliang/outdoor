package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.inter.IMyPlanAModel;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpRespon;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpUtil;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.bean.Activity;

public class MyPlanAModelImpl implements IMyPlanAModel {
    @Override
    public void getAll(final CallBack callBack) {

        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/activity/getAll";
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
}
