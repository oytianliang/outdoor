package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.inter.INodeAModel;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpRespon;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpUtil;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;



import com.bnuz.outdooractivitymanagementsystem.bean.ActivityNode;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
public class NodeAModelImpl implements INodeAModel {
    @Override
    public void getAllNodeByACTID(final int activityId, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/ActivityNode/getAllByActivityId?activityId="+activityId;
        String json = new Gson().toJson(activityId);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        httpUtil.getDate(url, body, new HttpRespon<ActivityNode[]>(ActivityNode[].class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }


            @Override
            public void onSuccess(ActivityNode[] activityNodes) {
                if(activityNodes!=null){
                    callBack.onSuccess(activityNodes);
                }
                else callBack.onError("没有活动");
            }


        });
    }

    @Override
    public void createNode(ActivityNode activityNode, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/ActivityNode/add";
        String json = new Gson().toJson(activityNode);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        httpUtil.getDate(url, body, new HttpRespon<ActivityNode>(ActivityNode.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(ActivityNode activityNode) {
                if(activityNode.getActivityId()>0){
                    callBack.onSuccess(activityNode);
                }
                else if(activityNode.getActivityId()==-1)
                    callBack.onError("节点已存在");
                else if (activityNode.getActivityId()==-2)
                    callBack.onError("活动不存在");
            }


        });
    }

    @Override
    public void deleteNode(ActivityNode activityNode, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/ActivityNode/delete";
        String json = new Gson().toJson(activityNode);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        httpUtil.getDate(url, body, new HttpRespon<String>(String.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(String s) {
                if(s.equals("ok")){
                    callBack.onSuccess("成功");
                }
                else if(s.equals("NodeNotFind"))
                    callBack.onError("节点不存在");
            }


        });
    }

    @Override
    public void updateNode(ActivityNode activityNode, final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/ActivityNode/setNode";
        String json = new Gson().toJson(activityNode);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        httpUtil.getDate(url, body, new HttpRespon<String>(String.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误");
            }

            @Override
            public void onSuccess(String s) {
                if(s.equals("ok")){
                    callBack.onSuccess("成功");
                }
                else if(s.equals("NotFind"))
                    callBack.onError("节点不存在");
            }


        });
    }
}
