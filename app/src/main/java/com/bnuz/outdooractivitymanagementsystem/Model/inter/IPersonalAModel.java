package com.bnuz.outdooractivitymanagementsystem.Model.inter;

import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;

public interface IPersonalAModel {
    void getUser( int userId,  CallBack callBack);
}
