package com.bnuz.outdooractivitymanagementsystem.Presenter.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.impl.InterfaceAModelImpl;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.IInterfaceAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IInterfaceAPresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IInterfaceAView;
import com.bnuz.outdooractivitymanagementsystem.bean.Activity;
import java.util.ArrayList;
import java.util.List;

public class InterfaceAPresenterImpl implements IInterfaceAPresenter {
    private IInterfaceAView mIInterfaceAView;
    private IInterfaceAModel mIInterfaceAModel;
    private List<Activity> list= new ArrayList<Activity>();

    public InterfaceAPresenterImpl(IInterfaceAView aIInterfaceAView) {
        mIInterfaceAView = aIInterfaceAView;
        mIInterfaceAModel = new InterfaceAModelImpl();

    }

    @Override
    public void searchManagement() {
        if(mIInterfaceAView.getSearchText().isEmpty()){
            mIInterfaceAView.showToast("输入为空");
        }else{
            mIInterfaceAModel.searchManagement(mIInterfaceAView.getSearchText(),new CallBack(){

                @Override
                public void onSuccess(Object response) {
                    list.addAll((List)response);
                    //在搜索界面显示出来，搜索界面（暂无）
                }

                @Override
                public void onError(String t) {

                }
            });
        }
    }
}
