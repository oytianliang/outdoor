package com.bnuz.outdooractivitymanagementsystem.Model.inter;

import com.bnuz.outdooractivitymanagementsystem.bean.ActivityNode;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;

public interface INodeAModel {
    void getAllNodeByACTID( int activityId,  CallBack callBack);//获取该活动所有节点
    void createNode(ActivityNode activityNode, CallBack callBack);//创建节点
    void deleteNode(ActivityNode activityNode, CallBack callBack);//删除节点
    void updateNode(ActivityNode activityNode, CallBack callBack);//更新节点
}
