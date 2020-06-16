package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bnuz.outdooractivitymanagementsystem.bean.User;
import com.bnuz.outdooractivitymanagementsystem.Presenter.impl.LoginAPresenterImpl;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.ILoginAPresenter;
import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.SPUtil;
import com.bnuz.outdooractivitymanagementsystem.View.inter.ILoginAView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginAView {

    private Button loginButton;
    private Button registerButton;
    private TextInputEditText loginName;
    private TextInputEditText loginPassword;

    private ILoginAPresenter mILoginAPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mILoginAPresenter = new LoginAPresenterImpl(this);
        setContentView(R.layout.activity_login);

        initViewBind();

    }

    private void initViewBind(){
        //初始化button控件
        loginButton = (Button) findViewById(R.id.b_btn_login);
        registerButton = (Button) findViewById(R.id.b_btn_register);
        loginName = (TextInputEditText)findViewById(R.id.e_user_name);
        loginPassword=(TextInputEditText)findViewById(R.id.e_user_password);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

    }

    /**
     * 以下是交互逻辑代码
     * */

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_btn_login:
                //这里写按钮逻辑
                mILoginAPresenter.login();
                break;
            case R.id.b_btn_register:
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent2);//切换成注册页面
                break;

        }
    }

    @Override
    public <T> T request(int requestFlag) {
        return null;
    }

    @Override
    public <T> void response(T response, int responseFlag) {

        if(responseFlag==ILoginAView.RESPONSE_ONE){
            User user =(User)response;
            String json = new Gson().toJson(user);//转换成String
            SPUtil.put(this,"User",json);//存入share_data;

            Intent intent = new Intent(this, InterfaceActivity.class);
            startActivity(intent);//切换成主页面
        }

    }

    @Override
    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /*对外部提供获取用户账号密码方法*/
    @Override
    public String getName(){
        return loginName.getText().toString();
    }

    @Override
    public String getPassword(){
        return loginPassword.getText().toString();
    }


}
