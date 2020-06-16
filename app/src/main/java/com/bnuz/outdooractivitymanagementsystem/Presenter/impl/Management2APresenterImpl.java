package com.bnuz.outdooractivitymanagementsystem.Presenter.impl;

import android.os.Looper;

import com.bnuz.outdooractivitymanagementsystem.Model.impl.Management2AModelImpl;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.IManagement2AModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IManagement2APresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IManagement2AView;

public class Management2APresenterImpl implements IManagement2APresenter {
    private IManagement2AView mIManagement2AView;
    private IManagement2AModel mIManagement2AModel;

    public Management2APresenterImpl(IManagement2AView aIManagement2AView) {
        mIManagement2AView = aIManagement2AView;
        mIManagement2AModel = new Management2AModelImpl();


    }

    @Override
    public void getACTByACTID(int ActId) {
        mIManagement2AModel.getACTByACTID(ActId, new CallBack() {
            @Override
            public void onSuccess(Object response) {
                mIManagement2AView.response(response,IManagement2AView.RESPONSE_ONE);//成功
                Looper.prepare();
                mIManagement2AView.showToast("获取活动成功");
                Looper.loop();
            }

            @Override
            public void onError(String t) {
                mIManagement2AView.response("获取活动失败", IManagement2AView.RESPONSE_TWO);
                Looper.prepare();
                mIManagement2AView.showToast(t);
                Looper.loop();
            }
        });
    }

    @Override
    public void getUserByACTId(int userId) {
        mIManagement2AModel.getUserByACTId(userId, new CallBack() {
            @Override
            public void onSuccess(Object response) {
                mIManagement2AView.response(response,IManagement2AView.RESPONSE_TWO);//成功
                Looper.prepare();
                mIManagement2AView.showToast("获取个人信息成功");
                Looper.loop();
            }

            @Override
            public void onError(String t) {
                mIManagement2AView.response("获取个人信息失败", IManagement2AView.RESPONSE_TWO);
                Looper.prepare();
                mIManagement2AView.showToast(t);
                Looper.loop();
            }
        });
    }
}
