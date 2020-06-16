package com.bnuz.outdooractivitymanagementsystem.Presenter.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.impl.PersonalAModelImpl;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.IPersonalAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IPersonalAPresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IPersonalAView;

public class PersonalAPresenterImpl implements IPersonalAPresenter {
    private IPersonalAView mIPersonalAView;
    private IPersonalAModel mIPersonalAModel;

    public PersonalAPresenterImpl(IPersonalAView aIPersonalAView) {
        mIPersonalAView = aIPersonalAView;
        mIPersonalAModel = new PersonalAModelImpl();
    }
}
