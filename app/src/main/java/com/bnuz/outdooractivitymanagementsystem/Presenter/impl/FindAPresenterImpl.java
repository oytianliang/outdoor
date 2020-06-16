package com.bnuz.outdooractivitymanagementsystem.Presenter.impl;


import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IFindAPresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IFindAView;

public class FindAPresenterImpl implements IFindAPresenter {
    private IFindAView mIFindAView;

    public FindAPresenterImpl(IFindAView aIFindAView) {
        mIFindAView = aIFindAView;
    }
}
