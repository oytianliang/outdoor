package com.bnuz.outdooractivitymanagementsystem.Model.inter;

import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;

public interface IManagementAModel {
    void getById(int activityId, CallBack callBack);
}
