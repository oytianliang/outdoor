package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.inter.ILoginAModel;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpRespon;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpUtil;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.bean.User;

public class LoginAModelImpl implements ILoginAModel {

    @Override
    public void login(User user, final CallBack callBack){
            HttpUtil httpUtil = HttpUtil.getInstance();
            String userName=user.getUserName();
            String userPassword=user.getPassword();
            String url = "http://101.37.202.16:8080/back-spring/user/signIn?name="+userName+"&password="+userPassword;//登录
            httpUtil.getDate(url, null, new HttpRespon<User>(User.class) {
                @Override
                public void onSuccess(User result) {
                    if(result.getUserId()==-1) {
                        callBack.onError("用户名不存在");
                    }else if(result.getUserId()==0){
                        callBack.onError("密码错误");
                    }else
                        callBack.onSuccess(result);
                }
                @Override
                public void onError(String msg) {
                    callBack.onError("服务器出错");
                }
            });



    }
}
