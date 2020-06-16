package com.bnuz.outdooractivitymanagementsystem.Presenter.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.impl.MyPlanAModelImpl;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.IMyPlanAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IMyPlanAPresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IMyPlanAView;

public class MyPlanAPresenterImpl implements IMyPlanAPresenter {
    private IMyPlanAView mIMyPlanAView;
    private IMyPlanAModel mIMyPlanAModel;

    public MyPlanAPresenterImpl(IMyPlanAView aIMyPlanAView) {
        mIMyPlanAView = aIMyPlanAView;
        mIMyPlanAModel = new MyPlanAModelImpl();
    }
}
