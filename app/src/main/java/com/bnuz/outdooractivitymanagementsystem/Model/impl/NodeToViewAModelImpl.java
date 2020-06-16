//package com.bnuz.outdooractivitymanagementsystem.Model.impl;
//
//import com.bnuz.outdooractivitymanagementsystem.Model.inter.INodeToViewAModel;
//import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpRespon;
//import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpUtil;
//import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
//
//
//
//import com.bnuz.outdooractivitymanagementsystem.bean.ActivityNode;
//import com.google.gson.Gson;
//
//import okhttp3.MediaType;
//import okhttp3.RequestBody;
//public class NodeToViewAModelImpl implements INodeToViewAModel {
//    @Override
//    public void getNodeByACTID(final int activityId, final CallBack callBack) {
//        HttpUtil httpUtil = HttpUtil.getInstance();
//        String url = "http://101.37.202.16:8080/back-spring/ActivityNode/getAllByActivityId?activityId="+activityId;
//        String json = new Gson().toJson(activityId);
//        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
//
//        httpUtil.getDate(url, body, new HttpRespon<ActivityNode[]>(ActivityNode[].class) {
//            @Override
//            public void onError(String msg) {
//                callBack.onError("服务器错误");
//            }
//
//
//            @Override
//            public void onSuccess(ActivityNode[] activityNodes) {
//                if(activityNodes!=null){
//                    callBack.onSuccess(activityNodes);
//                }
//                else callBack.onError("没有活动");
//            }
//
//
//        });
//    }
//}
