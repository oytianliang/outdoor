package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bnuz.outdooractivitymanagementsystem.Presenter.impl.MainAPresenterImpl;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IMainAPresenter;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IMainAView;
import com.bnuz.outdooractivitymanagementsystem.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IMainAView {

    private Button bt_gz;
    private Button bt_sys;
    private Button bt_fhd;
    private Button bt_wdqz;
    private BottomNavigationView bottomNavigationView;//底部导航栏
    private IMainAPresenter mIMainAPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIMainAPresenter = new MainAPresenterImpl(this);
        setContentView(R.layout.activity_main);

        //初始化button控件
        bt_gz = (Button) findViewById(R.id.bt_gz);
        bt_sys = (Button) findViewById(R.id.bt_sys);
        bt_fhd = (Button) findViewById(R.id.bt_fhd);
        bt_wdqz = (Button) findViewById(R.id.bt_wdqz);

        bottomNavigationView = findViewById(R.id.bottom_navigation);//绑定导航栏
        bottomNavigationView.setSelectedItemId(R.id.center);//设置当前所选
        //导航栏点击监听事件
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent1 = new Intent(MainActivity.this, InterfaceActivity.class);
                        startActivity(intent1);//切换成主页面
                        break;
                    case R.id.center:
                        Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent2);//切换成中心页面
                        break;
                    case R.id.explore:
                        Intent intent3 = new Intent(MainActivity.this, FindActivity.class);
                        startActivity(intent3);//切换成发现页面
                        break;
                    case R.id.mail:
                        Intent intent4 = new Intent(MainActivity.this, NewsActivity.class);
                        startActivity(intent4);//切换成消息页面
                        break;
                    case R.id.self:
                        Intent intent5 = new Intent(MainActivity.this, PersonalActivity.class);
                        startActivity(intent5);//切换成个人页面
                        break;
                }
                return true;
            }
        });

        bt_gz.setOnClickListener(this);
        bt_sys.setOnClickListener(this);
        bt_fhd.setOnClickListener(this);
        bt_wdqz.setOnClickListener(this);
    }

    /**
     * 以下是按钮部分逻辑代码
     */


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_gz:
                //这里写按钮逻辑
                break;
            case R.id.bt_sys:
                break;
            case R.id.bt_fhd:
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);//切换成发布活动页面
                break;
            case R.id.bt_wdqz:
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
