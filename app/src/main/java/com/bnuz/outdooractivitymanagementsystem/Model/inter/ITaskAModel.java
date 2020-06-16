package com.bnuz.outdooractivitymanagementsystem.Model.inter;

import com.bnuz.outdooractivitymanagementsystem.bean.Task;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;

public interface ITaskAModel {
    void createTask(Task task, CallBack callBack);//创建任务
    void deleteTask(Task task, CallBack callBack);//删除任务
    void getAllTaskByActivityID(int activityId, CallBack callBack);//获取该活动的所有任务
}
