package com.bnuz.outdooractivitymanagementsystem.Model.inter;

import com.bnuz.outdooractivitymanagementsystem.bean.SignInUser;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;

public interface IClockinAModel {
    void clockin(SignInUser signInUser, CallBack  callback);
}
