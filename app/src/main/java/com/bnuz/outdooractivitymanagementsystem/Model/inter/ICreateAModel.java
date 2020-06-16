package com.bnuz.outdooractivitymanagementsystem.Model.inter;

import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.bean.Activity;

public interface ICreateAModel {
    void createactivity(Activity activity, CallBack callBack);
}
