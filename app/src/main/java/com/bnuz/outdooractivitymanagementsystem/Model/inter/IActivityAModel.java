package com.bnuz.outdooractivitymanagementsystem.Model.inter;

import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;

public interface IActivityAModel {
    void getAll(int page, final CallBack callBack);
    void getCount(final CallBack callBack);
    void delete(int activityId,final CallBack callBack);
}
