package com.bnuz.outdooractivitymanagementsystem.Presenter.inter;

import com.bnuz.outdooractivitymanagementsystem.bean.Activity;

import java.io.File;

public interface ICreateAPresenter {
    void LogicCheck(Activity activity, File photoFile);
}
