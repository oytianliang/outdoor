package com.bnuz.outdooractivitymanagementsystem.Presenter.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.impl.MainAModelImpl;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.IMainAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IMainAPresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IMainAView;

public class MainAPresenterImpl implements IMainAPresenter {
    private IMainAView mIMainAView;
    private IMainAModel mIMainAModel;

    public MainAPresenterImpl(IMainAView aIMainAView) {
        mIMainAView = aIMainAView;
        mIMainAModel = new MainAModelImpl();
    }
}
