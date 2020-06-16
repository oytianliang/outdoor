package com.bnuz.outdooractivitymanagementsystem.ToolUtils;

import android.text.TextUtils;

public abstract  class HttpRespon<T> {

    //http返回的类型的泛型
    Class<T> t;

    public HttpRespon(Class<T> t){
        this.t = t;
    }
    //请求失败，告诉调用者失败的原因
    public abstract void onError(String msg);
    //请求成功，http返回需要的类型，把类型传给onSuccess(T t),该方法对数据进行进一步处理
    public abstract void onSuccess(T t);

    public void prase(String json){
        if (TextUtils.isEmpty(json)){
            //请求失败
            onError("连接网络失败");
            return;
        }
        //如果只需要获取json对象，不用解析
        if (t==String.class){
            onSuccess((T) json);//json传回去
            return;
        }

        //尝试把json转化为我们需要的类型
        T result = JsonUtil.parseJson(json,t);

        //转化成功
        if (result!=null){
            //请求成功
            onSuccess(result);//在这里对数据返回的数据进行进一步处理
        }else{
            onError("json解析失败");
        }
    }
}
