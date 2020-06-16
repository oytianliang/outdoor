package com.bnuz.outdooractivitymanagementsystem.Presenter.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.impl.SignInAModelImpl;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.ISignInAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IClockinAPresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IClockinAView;

public class ClockinAPresenterImpl implements IClockinAPresenter {
    private IClockinAView mIClockinAView;
    private ISignInAModel mISignInAModel;

    public ClockinAPresenterImpl(IClockinAView aIClockinAView) {
        mIClockinAView = aIClockinAView;
        mISignInAModel = new SignInAModelImpl();
    }
}
