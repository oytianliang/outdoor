package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.inter.IInterestAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.bean.User;

public class InterestAModelImpl implements IInterestAModel {
    public void addIntertest(final User user, final CallBack callBack){

        callBack.onSuccess("成功");

    }

}

