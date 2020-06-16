package com.bnuz.outdooractivitymanagementsystem.Presenter.impl;

import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IManagement3APresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IManagement3AView;

public class Management3APresenterImpl implements IManagement3APresenter {
    private IManagement3AView mIManagement3AView;
//    private IManagement3AModel mIManagement3AModel;

    public Management3APresenterImpl(IManagement3AView aIManagement3AView) {
        mIManagement3AView = aIManagement3AView;
//        mIManagement3AModel = new Management3AModelImpl();
    }
}
