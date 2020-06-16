package com.bnuz.outdooractivitymanagementsystem.Presenter.impl;

import android.os.Looper;

import com.bnuz.outdooractivitymanagementsystem.Model.impl.LoginAModelImpl;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.ILoginAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.ILoginAPresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.ILoginAView;
import com.bnuz.outdooractivitymanagementsystem.bean.User;

public class LoginAPresenterImpl implements ILoginAPresenter {

    private ILoginAView mILoginAView;
    private ILoginAModel mILoginAModel;

    public LoginAPresenterImpl(ILoginAView aILoginAView) {
        mILoginAView = aILoginAView;
        mILoginAModel = new LoginAModelImpl();
    }

    @Override
    public void login(){
        if(mILoginAView.getName().isEmpty()||mILoginAView.getPassword().isEmpty()){
            mILoginAView.showToast("请输入用户名或者密码");
        }else{
            final User user=new User();
            user.setUserName(mILoginAView.getName());
            user.setPassword(mILoginAView.getPassword());
            mILoginAModel.login(user,new CallBack(){
                @Override
                public void onSuccess(Object response){//匹配成功
                    mILoginAView.response(response,ILoginAView.RESPONSE_ONE);//把数据库获得的用户传递回VIEW

                    Looper.prepare();
                    mILoginAView.showToast("登录成功");
                    Looper.loop();
                }

                @Override
                public void onError(String t){//匹配失败
                    mILoginAView.response("对不起，没找到",ILoginAView.RESPONSE_TWO);
                    Looper.prepare();
                    mILoginAView.showToast(t);
                    Looper.loop();
                }
            });

        }
    }
}
