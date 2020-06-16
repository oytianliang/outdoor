package com.bnuz.outdooractivitymanagementsystem.Model.inter;

import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;

public interface IGetUserAModel {
    void getUserById(int userId, CallBack callBack);
    void getUserByName(String name, CallBack callBack);

}