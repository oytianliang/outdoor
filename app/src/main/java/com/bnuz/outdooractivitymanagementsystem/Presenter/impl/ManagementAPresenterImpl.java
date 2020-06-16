package com.bnuz.outdooractivitymanagementsystem.Presenter.impl;

import android.os.Looper;

import com.bnuz.outdooractivitymanagementsystem.Model.impl.ManagementAModelImpl;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.IManagementAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IManagementAPresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IManagementAView;

public class ManagementAPresenterImpl implements IManagementAPresenter {
    private IManagementAView mIManagementAView;
    private IManagementAModel mIManagementAModel;

    public ManagementAPresenterImpl(IManagementAView aIManagementAView) {
        mIManagementAView = aIManagementAView;
        mIManagementAModel = new ManagementAModelImpl();
    }

    @Override
    public void getActivity(int ActId) {
        mIManagementAModel.getById(ActId, new CallBack() {
            @Override
            public void onSuccess(Object response) {
                mIManagementAView.response(response,IManagementAView.RESPONSE_ONE);
                Looper.prepare();
                mIManagementAView.showToast("活动获取成功");
                Looper.loop();
            }

            @Override
            public void onError(String t) {
                mIManagementAView.response("获取活动失败", IManagementAView.RESPONSE_TWO);
                Looper.prepare();
                mIManagementAView.showToast(t);
                Looper.loop();
            }
        });
    }
}
