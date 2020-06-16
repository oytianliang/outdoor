package com.bnuz.outdooractivitymanagementsystem.Model.inter;

import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.bean.ActivityUser;

public interface IActivityUserAModel {
    void AttendActivity(ActivityUser activityUser,CallBack callBack);  //参加活动
    void DeleteUserInActivity(int activityId,int userId, CallBack callBack); //从活动里面删除成员
    void UpdateAuthority(ActivityUser activityUser,CallBack callBack);  //更新某成员权限
    void getActivitysByUserId(int activityId,CallBack callBack);  //获得参加活动的所有用户Id
}
