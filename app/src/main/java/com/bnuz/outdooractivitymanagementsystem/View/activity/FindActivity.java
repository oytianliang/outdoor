package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bnuz.outdooractivitymanagementsystem.Presenter.impl.FindAPresenterImpl;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IFindAPresenter;
import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IFindAView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FindActivity extends AppCompatActivity implements View.OnClickListener, IFindAView {

    private BottomNavigationView bottomNavigationView;
    private IFindAPresenter mIFindAPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIFindAPresenter = new FindAPresenterImpl(this);
        setContentView(R.layout.activity_find);
        //初始化控件
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.explore);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent1 = new Intent(FindActivity.this, InterfaceActivity.class);
                        startActivity(intent1);//切换成主页面
                        break;
                    case R.id.center:
                        Intent intent2 = new Intent(FindActivity.this, MainActivity.class);
                        startActivity(intent2);//切换成中心页面
                        break;
                    case R.id.explore:
                        Intent intent3 = new Intent(FindActivity.this, FindActivity.class);
                        startActivity(intent3);//切换成发现页面
                        break;
                    case R.id.mail:
                        Intent intent4 = new Intent(FindActivity.this, NewsActivity.class);
                        startActivity(intent4);//切换成消息页面
                        break;
                    case R.id.self:
                        Intent intent5 = new Intent(FindActivity.this, PersonalActivity.class);
                        startActivity(intent5);//切换成个人页面
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home:
                Intent intent1 = new Intent(FindActivity.this, InterfaceActivity.class);
                startActivity(intent1);//切换成主页面
                break;
            case R.id.center:
                Intent intent2 = new Intent(FindActivity.this, MainActivity.class);
                startActivity(intent2);//切换成中心页面
                break;
            case R.id.explore:
                Intent intent3 = new Intent(FindActivity.this, FindActivity.class);
                startActivity(intent3);//切换成发现页面
                break;
            case R.id.mail:
                Intent intent4 = new Intent(FindActivity.this, NewsActivity.class);
                startActivity(intent4);//切换成消息页面
                break;
            case R.id.self:
                Intent intent5 = new Intent(FindActivity.this, PersonalActivity.class);
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
