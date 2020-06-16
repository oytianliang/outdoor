package com.bnuz.outdooractivitymanagementsystem.Model.inter;

import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;

public interface IManagement2AModel {
    void getUserByACTId( int activityId,  CallBack callBack);
    void getACTByACTID(int activityId,  CallBack callBack);
}
