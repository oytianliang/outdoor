package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bnuz.outdooractivitymanagementsystem.Presenter.impl.MyPlanAPresenterImpl;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IMyPlanAPresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IMyPlanAView;
import com.bnuz.outdooractivitymanagementsystem.R;

public class MyPlanActivity extends AppCompatActivity implements View.OnClickListener, IMyPlanAView {

    private ImageButton planback;
    private Button myplan;
    private Button myjoin;
    private ImageButton home;
    private ImageButton center;
    private ImageButton explore;
    private ImageButton mail;
    private ImageButton self;
    private IMyPlanAPresenter mIMyPlanAPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIMyPlanAPresenter = new MyPlanAPresenterImpl(this);
        setContentView(R.layout.activity_my_plan);

        //初始化button控件
        planback = (ImageButton) findViewById(R.id.plan_back);
        myplan = (Button) findViewById(R.id.myplan);
        myjoin = (Button) findViewById(R.id.myjoin);
        home = (ImageButton) findViewById(R.id.home);
        center = (ImageButton) findViewById(R.id.center);
        explore = (ImageButton) findViewById(R.id.explore);
        mail = (ImageButton) findViewById(R.id.mail);
        self = (ImageButton) findViewById(R.id.self);

        planback.setOnClickListener(this);
        myplan.setOnClickListener(this);
        myjoin.setOnClickListener(this);
        home.setOnClickListener(this);
        center.setOnClickListener(this);
        explore.setOnClickListener(this);
        mail.setOnClickListener(this);
        self.setOnClickListener(this);
    }

    /**
     * 以下是按钮部分逻辑代码
     */

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plan_back:
                //这里写按钮逻辑
                break;
            case R.id.myplan:
                break;
            case R.id.myjoin:
                break;
            case R.id.home:
                Intent intent1 = new Intent(MyPlanActivity.this, InterfaceActivity.class);
                startActivity(intent1);//切换成主页面
                break;
            case R.id.center:
                Intent intent2 = new Intent(MyPlanActivity.this, MainActivity.class);
                startActivity(intent2);//切换成中心页面
                break;
            case R.id.explore:
                Intent intent3 = new Intent(MyPlanActivity.this, FindActivity.class);
                startActivity(intent3);//切换成发现页面
                break;
            case R.id.mail:
                Intent intent4 = new Intent(MyPlanActivity.this, NewsActivity.class);
                startActivity(intent4);//切换成消息页面
                break;
            case R.id.self:
                Intent intent5 = new Intent(MyPlanActivity.this, PersonalActivity.class);
                startActivity(intent5);//切换成个人页面
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
