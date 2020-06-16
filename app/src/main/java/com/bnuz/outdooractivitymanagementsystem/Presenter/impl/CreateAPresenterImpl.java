package com.bnuz.outdooractivitymanagementsystem.Presenter.impl;


import com.bnuz.outdooractivitymanagementsystem.Model.impl.CreateAModelImpl;
import com.bnuz.outdooractivitymanagementsystem.Model.impl.FileAModelImpl;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.ICreateAModel;
import com.bnuz.outdooractivitymanagementsystem.Model.inter.IFileAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.ICreateAPresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.ICreateAView;
import com.bnuz.outdooractivitymanagementsystem.bean.Activity;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CreateAPresenterImpl implements ICreateAPresenter {
    private ICreateAView mICreateAView;
    private ICreateAModel mICreateAModel;
    private IFileAModel mIFileAModel;
    private static final String DEFAULT_QUERY_REGEX = "[!$^&*+=|{}';'\",<>/?~！#￥%……&*——|{}【】‘；：”“'。，、？]";//正则表达式，去非法字符

    public CreateAPresenterImpl(ICreateAView aICreateAView) {
        mICreateAView = aICreateAView;
        mICreateAModel = new CreateAModelImpl();
        mIFileAModel = new FileAModelImpl();
    }

    @Override
    public void LogicCheck(final Activity activity, final File photoFile) {
        if(specialSymbols(activity.getActivityTitle())){//待补充
            mICreateAView.response("标题包含非法字符"+getQueryRegex(),mICreateAView.RESPONSE_TWO);
        } else if(activity.getActivityTitle().length()>10||activity.getDes().length()>100){
            mICreateAView.response("注意，字数超出限额，标题10字以内，简介100字以内"+getQueryRegex(),mICreateAView.RESPONSE_TWO);
        }else{
            mICreateAModel.createactivity(activity,new CallBack(){
                @Override
                public void onSuccess(Object response){//匹配成功
                    Activity activity2=(Activity)response;
                    if (photoFile != null) {
                        mIFileAModel.upLoadACTPhoto(activity2.getActivityId(),photoFile,new CallBack(){//拿到ID后，上传图片

                            @Override
                            public void onSuccess(Object response1) {
                                mICreateAView.response(response1,mICreateAView.RESPONSE_ONE);//上传成功返回url
                            }

                            @Override
                            public void onError(String t) {
                                mICreateAView.response(t,mICreateAView.RESPONSE_THREE);//上传失败，返回错误信息
                            }
                        });
                    }else
                        mICreateAView.response(response,mICreateAView.RESPONSE_TWO);//假设没有上传图片，返回结果

                }

                @Override
                public void onError(String t){//匹配失败
                    mICreateAView.response(t,mICreateAView.RESPONSE_THREE);
                }
            });
        }
    }


    /**
     * 判断查询参数中是否以特殊字符开头，如果以特殊字符开头则返回true，否则返回false
     * @param value
     * @return
     * @see {@link #getQueryRegex()}
     * @see {@link #DEFAULT_QUERY_REGEX}
     */
    public boolean specialSymbols(String value) {
        if (value.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(getQueryRegex());
        Matcher matcher = pattern.matcher(value);
        char[] specialSymbols = getQueryRegex().toCharArray();
        boolean isStartWithSpecialSymbol = false; // 是否以特殊字符开头
        for (int i = 0; i < specialSymbols.length; i++) {
            char c = specialSymbols[i];
            if (value.indexOf(c) == 0) {
                isStartWithSpecialSymbol = true;
                break;
            }
        }
        return matcher.find() && isStartWithSpecialSymbol;
    }
    /**
     * 获取查询过滤的非法字符
     * @return
     */
    private String getQueryRegex() {
        return DEFAULT_QUERY_REGEX;
    }
}
