package com.bnuz.outdooractivitymanagementsystem.Presenter.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.impl.FileAModelImpl;
import com.bnuz.outdooractivitymanagementsystem.Model.impl.PersoninfoAModelImpl;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.IFileAModel;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.IPersoninfoAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IPersoninfoAPresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IPersoninfoAView;
import com.bnuz.outdooractivitymanagementsystem.bean.User;

import java.io.File;

public class PersoninfoAPresenterImpl implements IPersoninfoAPresenter {
    private IPersoninfoAView mIPersoninfoAView;
    private IPersoninfoAModel mIPersoninfoAModel;
    private IFileAModel mIFileAModel;

    public PersoninfoAPresenterImpl(IPersoninfoAView aIPersoninfoAView) {
        mIPersoninfoAView = aIPersoninfoAView;
        mIPersoninfoAModel = new PersoninfoAModelImpl();
        mIFileAModel = new FileAModelImpl();
    }

    @Override
    public void save(final User user, final File file) {

        mIPersoninfoAModel.update(user, new CallBack() {
            @Override
            public void onSuccess(Object response) {
                if(user.getPassword()==null||user.getUserName()==null){
                    mIPersoninfoAView.response("密码为空或者用户名为空",IPersoninfoAView.RESPONSE_THREE);
                }

                if(file!=null){

                    mIFileAModel.upLoadProfile(user.getUserId(),file,new CallBack() {

                        @Override
                        public void onSuccess(Object response) {

                            mIPersoninfoAView.response(response,IPersoninfoAView.RESPONSE_ONE);//RESPONSE_ONE定义1为带图片结果
                        }

                        @Override
                        public void onError(String t) {

                            mIPersoninfoAView.response(t,IPersoninfoAView.RESPONSE_THREE);//RESPONSE_THREE失败

                        }
                    });

                }else{
                    mIPersoninfoAView.response(response,IPersoninfoAView.RESPONSE_TWO);//不带图
                }

            }

            @Override
            public void onError(String t) {
                mIPersoninfoAView.response("保存失败", mIPersoninfoAView.RESPONSE_THREE);

            }
        });




    }


}