package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.inter.IInterfaceAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;

public class InterfaceAModelImpl implements IInterfaceAModel {
    @Override
    public void searchManagement(String searchText, CallBack callBack) {
        //这里传入搜索值，到后台，返回相匹配的 活动类？ 成功后给活动类集合，失败了，返回错误信息（String)
    }
}
