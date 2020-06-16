package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bnuz.outdooractivitymanagementsystem.bean.User;
import com.bnuz.outdooractivitymanagementsystem.Presenter.impl.RegisterAPresenterImpl;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IRegisterAPresenter;
import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.SPUtil;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IRegisterAView;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, IRegisterAView {

    private Button register;
    private Button cancel;
    private EditText editName;
    private EditText editPassword;
    private EditText editPassword_repeat;

    private IRegisterAPresenter mIRegisterAPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIRegisterAPresenter = new RegisterAPresenterImpl(this);
        setContentView(R.layout.activity_register);

        initViewBind();
    }

    private void initViewBind(){
        //初始化button控件
        register = (Button) findViewById(R.id.b_btn_register);
        cancel = (Button) findViewById(R.id.b_btn_cancel);
        editName = (EditText) findViewById(R.id.e_user_register_name);
        editPassword = (EditText) findViewById(R.id.e_user_register_password);
        editPassword_repeat = (EditText) findViewById(R.id.e_user_confirm_password);




        register.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    public void onClick(View v) {//这里写按钮逻辑
        switch (v.getId()) {
            case R.id.b_btn_register:
                mIRegisterAPresenter.register();//调用注册函数
                break;
            case R.id.b_btn_cancel:
                Intent intent2 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent2);//切换成登录页面
                break;

        }
    }

    @Override
    public <T> T request(int requestFlag) {
        return null;
    }

    @Override
    public <T> void response(T response, int responseFlag) {

        if(responseFlag==IRegisterAView.RESPONSE_ONE){
            String json=new Gson().toJson((User)response);
            SPUtil.put(RegisterActivity.this,"activity",json);

            Intent intent = new Intent(this, InterestActivity.class);
            startActivity(intent);//切换成兴趣爱好界面
        }
    }



    @Override
    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /*对外部提供获取注册用户账号密码方法*/
    @Override
    public String getName(){ return editName.getText().toString(); }

    @Override
    public String getPassword(){
        return editPassword.getText().toString();
    }

    @Override
    public String getPassword_repeat(){
        return editPassword_repeat.getText().toString();
    }

}
