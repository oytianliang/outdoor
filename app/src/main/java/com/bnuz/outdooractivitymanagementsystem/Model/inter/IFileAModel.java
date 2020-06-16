package com.bnuz.outdooractivitymanagementsystem.Model.inter;

import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;

import java.io.File;

public interface IFileAModel {
    void upLoadProfile(int userId, File file, CallBack callBack);//头像
    void upLoadACTPhoto(int activityId,File file, CallBack callBack);//活动图片
}
