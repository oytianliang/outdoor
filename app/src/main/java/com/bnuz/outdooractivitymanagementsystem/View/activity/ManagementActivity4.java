package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IManagement4APresenter;
import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IManagement4AView;
public class ManagementActivity4 extends AppCompatActivity implements View.OnClickListener,IManagement4AView {

    private IManagement4APresenter mIManagement4APresenter;
    //以下是管理活动用户页面控件
    private ImageButton ImageButton_user1_1;
    private ImageButton ImageButton_user1_2;
    //这里暂时省略,之后考虑自动创建
    private Button button_createNode;
    private Button button_allClockIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mIManagement4APresenter = new Management4APresenterImpl(this);
        setContentView(R.layout.activity_management4);

        ImageButton_user1_1= findViewById(R.id.imageButton5);
        ImageButton_user1_2= findViewById(R.id.imageButton4);

        button_createNode=findViewById(R.id.button13);
        button_allClockIn = findViewById(R.id.button14);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button13:
                //这里写按钮逻辑
                break;
            case R.id.button14:
                break;
            case R.id.imageButton5:
                break;
            case R.id.imageButton4:
                break;
        }
    }

    @Override
    public <T> T request(int requestFlag) {
        return null;
    }

    @Override
    public <T> void response(T response, int responseFlag) {

    }

    @Override
    public <T> void showToast(String msg) {

    }


}
