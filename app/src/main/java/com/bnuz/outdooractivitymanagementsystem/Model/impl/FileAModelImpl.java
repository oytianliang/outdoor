package com.bnuz.outdooractivitymanagementsystem.Model.impl;

import com.bnuz.outdooractivitymanagementsystem.Model.inter.IFileAModel;
import com.bnuz.outdooractivitymanagementsystem.Presenter.callback.CallBack;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpRespon;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.HttpUtil;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FileAModelImpl implements IFileAModel {
    @Override
    public void upLoadProfile(int userId, File file, final  CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/upload/headSave?userId="+userId;
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart(
                "file",
                file.getName(),
                RequestBody.create(MediaType.parse("image/png"), file)
        ).build();

        httpUtil.getDate(url, body, new HttpRespon<String>(String.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("照片上传服务器错误");
            }


            @Override
            public void onSuccess(String s) {
                if(s.equals("fileNotFind")){
                    callBack.onError("文件不存在");
                }else if (s.equals("upLoadFail")){
                    callBack.onError("上传失败");
                }else if(s.equals("userNotExist")){
                    callBack.onError("用户id错误");
                } else callBack.onSuccess(s);//传回URL
            }


        });
    }

    @Override
    public void upLoadACTPhoto(int activityId,File file,final CallBack callBack) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        String url = "http://101.37.202.16:8080/back-spring/upload/activitySave?activityId="+activityId;
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart(
                "file",
                file.getName(),
                RequestBody.create(MediaType.parse("image/png"),file)
        ).build();
        httpUtil.getDate(url, body, new HttpRespon<String>(String.class) {
            @Override
            public void onError(String msg) {
                callBack.onError("服务器错误（上传活动图片）");
            }


            @Override
            public void onSuccess(String s) {
                if(s.equals("fileNotFind")){
                    callBack.onError("文件不存在");
                } else if (s.equals("upLoadFail")){
                    callBack.onError("上传失败");
                }else if(s.equals("userNotExist")){
                    callBack.onError("活动id错误");
                }else callBack.onSuccess(s);//成功就传回URL

            }


        });
    }
}
