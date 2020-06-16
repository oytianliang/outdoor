package com.bnuz.outdooractivitymanagementsystem.ToolUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    static HttpUtil util;
    static OkHttpClient client;

    //这个构造方法永远只会被调用一次
    private HttpUtil(){
        //设置http的超时处理
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

    }


    //单例的方法
    public static  HttpUtil getInstance(){
        if (util==null){
            synchronized (HttpUtil.class){
                if (util==null){
                    util= new HttpUtil();
                }
            }
        }
        return util;
    }

    //通过调用这个方法来获取数据传入url和respon 两个参数
    public void getDate(String url, RequestBody requestBody, final HttpRespon respon){
        Request request;
        if(requestBody!=null){
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
        }else {
            request = new Request.Builder()
                    .url(url)
                    .build();
        }


        //开启一个异步请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                respon.onError("连接服务器失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    //请求失败
                }
                //获取到接口的数据json格式
                String date = response.body().string();
                respon.prase(date);
            }
        });
    }
}