package com.bnuz.outdooractivitymanagementsystem.View.inter;

import com.bnuz.outdooractivitymanagementsystem.bean.Activity;

public interface ICreateAView extends BaseView{

    //如果需要外部调用activity内的方法，现在下面定义
    <T> void saveActivity(Activity activity);
}
