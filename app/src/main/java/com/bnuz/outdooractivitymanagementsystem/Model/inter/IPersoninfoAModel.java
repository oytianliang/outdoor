package com.bnuz.outdooractivitymanagementsystem.Model.inter;

import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.bean.User;

public interface IPersoninfoAModel {
    void update(User user, CallBack callBack);
}
