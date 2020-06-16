package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.bean.Task;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.ITaskAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpRespon;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpUtil;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TaskAModelImpl implements ITaskAModel {
    @Override
    public void createTask(Task task,final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/Task/add";
        String json = new Gson().toJson(task);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        httpUtil.getDate(url, body, new HttpRespon<Task>(Task.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(Task task) {
                if(task.getTaskId()>0)
                    callBack.onSuccess(task);
                else if(task.getTaskId()==0)
                    callBack.onError("活动不存在");
                else
                    callBack.onError("增加任务失败");//不存在
            }
        });
    }

    @Override
    public void deleteTask(Task task,final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/Task/delete";
        String json = new Gson().toJson(task);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        httpUtil.getDate(url, body, new HttpRespon<String>(String.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(String s) {
                if(s.equals("ok"))
                    callBack.onSuccess("删除成功");
                else if(s.equals("TaskNotFind"))
                    callBack.onError("任务不存在");
                else
                    callBack.onError("删除任务失败");//不存在
            }
        });
    }

    @Override
    public void getAllTaskByActivityID(int activityId,final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/Task/getAllByActivityId?activityId="+activityId;
         httpUtil.getDate(url, null, new HttpRespon<Task[]>(Task[].class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(Task[] tasks) {
                if(tasks!=null)
                    callBack.onSuccess(tasks);
                else
                    callBack.onError("获取所有任务失败");//不存在
            }
        });
    }
}
