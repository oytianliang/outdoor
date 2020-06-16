package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bnuz.outdooractivitymanagementsystem.Presenter.impl.InterestAPresenterImpl;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IInterestAPresenter;
import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.SPUtil;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IInterestAView;
import com.bnuz.outdooractivitymanagementsystem.bean.User;
import com.google.gson.Gson;

public class InterestActivity extends AppCompatActivity implements View.OnClickListener, IInterestAView {

    String json= (String) SPUtil.get(InterestActivity.this,"User","1");
    User user=new Gson().fromJson(json,User.class);//获取本地用户类
    IInterestAPresenter mIInterestAPresenter = new InterestAPresenterImpl(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_interest);

        //初始化控件
        //兴趣爱好选项按钮 后期按需添加
        Button interest1 = (Button) findViewById(R.id.interest1);
        Button interest2 = (Button) findViewById(R.id.截至时间btn);
        Button interest3 = (Button) findViewById(R.id.结束时间btn);
        Button interest4 = (Button) findViewById(R.id.button9);
        Button cancel = (Button) findViewById(R.id.cancel);

        interest1.setOnClickListener(this);
        interest2.setOnClickListener(this);
        interest3.setOnClickListener(this);
        interest4.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.interest1:
                user.setInterest("徒步旅游");
                mIInterestAPresenter.addIntertest(user);
                break;
            case R.id.截至时间btn:
                user.setInterest("自驾游");
                mIInterestAPresenter.addIntertest(user);
                break;
            case R.id.结束时间btn:
                user.setInterest("音乐会");
                mIInterestAPresenter.addIntertest(user);
                break;
            case R.id.button9:
                user.setInterest("演唱会");
                mIInterestAPresenter.addIntertest(user);
                break;
            case R.id.cancel:
                Intent intent = new Intent(InterestActivity.this, InterfaceActivity.class);
                startActivity(intent);//切换成主页面
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
    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
