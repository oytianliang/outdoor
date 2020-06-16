package com.bnuz.outdooractivitymanagementsystem.Model.inter;

import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.bean.User;

public interface IRegisterAModel {
    void register(User user, CallBack callBack);
}
