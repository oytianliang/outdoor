package com.bnuz.outdooractivitymanagementsystem.Presenter.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.impl.InterestAModelImpl;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.IInterestAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IInterestAPresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IInterestAView;
import com.bnuz.outdooractivitymanagementsystem.bean.User;

public class InterestAPresenterImpl implements IInterestAPresenter {
    private IInterestAView mIInterestAView;
    private IInterestAModel mIInterestAModel;

    public InterestAPresenterImpl(IInterestAView aIInterestAView) {
        mIInterestAView = aIInterestAView;
        mIInterestAModel = new InterestAModelImpl();
    }

    @Override
    public void addIntertest(User user) {
        mIInterestAModel.addIntertest(user, new CallBack() {
            @Override
            public void onSuccess(Object response) {//添加兴趣成功
                mIInterestAView.response("成功", mIInterestAView.RESPONSE_ONE);

            }

            @Override
            public void onError(String t) {//添加兴趣失败
                mIInterestAView.response(t, mIInterestAView.RESPONSE_TWO);

            }
        });
    }


}
