package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bnuz.outdooractivitymanagementsystem.Presenter.impl.PersonalAPresenterImpl;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IPersonalAPresenter;
import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.DataUtil;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.SPUtil;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IPersonalAView;
import com.bnuz.outdooractivitymanagementsystem.bean.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

public class PersonalActivity extends AppCompatActivity implements View.OnClickListener, IPersonalAView {

    private ImageButton headPhoto;
    private TextView userName;
    private Button myativity;
    private Button ativitymange;
    private Button myfriend;
    private Button mygroup;
    private BottomNavigationView bottomNavigationView;
    private User user;
    private Uri photoUri;


    private static final int UP_USER_DATA =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String json = (String) SPUtil.get(PersonalActivity.this, "User", "1");
        user = new Gson().fromJson(json, User.class);//获取本地用户类

        IPersonalAPresenter mIPersonalAPresenter = new PersonalAPresenterImpl(this);
        setContentView(R.layout.activity_personal);



        //初始化button控件
        myativity = (Button) findViewById(R.id.myativity);
        ativitymange = (Button) findViewById(R.id.ativitymange);
        myfriend = (Button) findViewById(R.id.myfriend);
        mygroup = (Button) findViewById(R.id.mygroup);

        headPhoto = (ImageButton) findViewById(R.id.头像按钮);
        userName=(TextView) findViewById(R.id.用户名);

        Bitmap bitmap= DataUtil.returnBitMap(user.getHead());
        headPhoto.setImageBitmap(bitmap);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.self);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent1 = new Intent(PersonalActivity.this, InterfaceActivity.class);
                        startActivity(intent1);//切换成主页面
                        break;
                    case R.id.center:
                        Intent intent2 = new Intent(PersonalActivity.this, MainActivity.class);
                        startActivity(intent2);//切换成中心页面
                        break;
                    case R.id.explore:
                        Intent intent3 = new Intent(PersonalActivity.this, FindActivity.class);
                        startActivity(intent3);//切换成发现页面
                        break;
                    case R.id.mail:
                        Intent intent4 = new Intent(PersonalActivity.this, NewsActivity.class);
                        startActivity(intent4);//切换成消息页面
                        break;
                    case R.id.self:
                        Intent intent5 = new Intent(PersonalActivity.this, PersonalActivity.class);
                        startActivity(intent5);//切换成个人页面
                        break;
                }
                return true;
            }
        });

        myativity.setOnClickListener(this);
        ativitymange.setOnClickListener(this);
        myfriend.setOnClickListener(this);
        mygroup.setOnClickListener(this);
        headPhoto.setOnClickListener(this);
    }

    /**
     * 以下是按钮部分逻辑代码
     */

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myativity:
                //这里写按钮逻辑


            break;
            case R.id.ativitymange:
                break;
            case R.id.myjoin:
                break;
            case R.id.头像按钮:
                Intent intent = new Intent(PersonalActivity.this, PersoninfoActivity.class);
                startActivityForResult(intent,UP_USER_DATA);//切换成修改个人信息页面
                break;
            case R.id.home:
                Intent intent1 = new Intent(PersonalActivity.this, InterfaceActivity.class);
                startActivity(intent1);//切换成个人页面
                break;
            case R.id.center:
                Intent intent2 = new Intent(PersonalActivity.this, MainActivity.class);
                startActivity(intent2);//切换成中心页面
                break;
            case R.id.explore:
                Intent intent3 = new Intent(PersonalActivity.this, FindActivity.class);
                startActivity(intent3);//切换成发现页面
                break;
            case R.id.mail:
                Intent intent4 = new Intent(PersonalActivity.this, NewsActivity.class);
                startActivity(intent4);//切换成消息页面
                break;
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//返回成功
            if (requestCode == UP_USER_DATA) {
               //返回信息修改页面的头像？
                photoUri=data.getData();
                headPhoto.setImageURI(photoUri);
            }
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

