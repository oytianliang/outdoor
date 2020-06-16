package com.bnuz.outdooractivitymanagementsystem.Presenter.impl;

import android.os.Looper;

import com.bnuz.outdooractivitymanagementsystem.Model.impl.RegisterAModelImpl;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.IRegisterAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IRegisterAPresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IRegisterAView;
import com.bnuz.outdooractivitymanagementsystem.bean.User;

public class RegisterAPresenterImpl implements IRegisterAPresenter {
    private IRegisterAView mIRegisterAView;
    private IRegisterAModel mIRegisterAModel;

    public RegisterAPresenterImpl(IRegisterAView aIRegisterAView) {
        mIRegisterAView = aIRegisterAView;
        mIRegisterAModel = new RegisterAModelImpl();
    }

    @Override
    public void register() {
        if(mIRegisterAView.getName().equals("")||mIRegisterAView.getPassword().equals("")){

            mIRegisterAView.showToast("请输入注册账号和密码");

        } else if (!mIRegisterAView.getPassword().matches(mIRegisterAView.getPassword_repeat())) {

            mIRegisterAView.showToast("两次输入的密码不相同，请重新输入");

        } else {
            final User user=new User();
            user.setUserName(mIRegisterAView.getName());
            user.setPassword(mIRegisterAView.getPassword());
            mIRegisterAModel.register(user, new CallBack() {
                @Override
                public void onSuccess(Object response) {//注册成功
                    mIRegisterAView.response(response, IRegisterAView.RESPONSE_ONE);

                    Looper.prepare();
                    mIRegisterAView.showToast("注册成功");
                    Looper.loop();
                }

                @Override
                public void onError(String t) {//注册失败
                    mIRegisterAView.response("注册失败", IRegisterAView.RESPONSE_TWO);

                    Looper.prepare();
                    mIRegisterAView.showToast(t);
                    Looper.loop();
                }
            });

        }
    }
}
