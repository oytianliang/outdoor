package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bnuz.outdooractivitymanagementsystem.Presenter.impl.PersoninfoAPresenterImpl;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IPersoninfoAPresenter;
import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.SPUtil;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IPersoninfoAView;
import com.bnuz.outdooractivitymanagementsystem.bean.User;
import com.bnuz.outdooractivitymanagementsystem.databinding.ActivityPersoninfoBinding;
import com.google.gson.Gson;

import java.io.File;

public class PersoninfoActivity extends AppCompatActivity implements IPersoninfoAView{

    private static final int UP_LOAD_PICTURE = 2;

    Uri photoUri;
    File photoFile;
    String photoPath;
    User localUser;

    IPersoninfoAPresenter mIPersoninfoAPresenter;
    ActivityPersoninfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String json = (String) SPUtil.get(PersoninfoActivity.this, "User", "1");
        localUser = new Gson().fromJson(json, User.class);//获取本地用户类

        binding = DataBindingUtil.setContentView(PersoninfoActivity.this, R.layout.activity_personinfo);//绑定布局页面
        binding.setUser(localUser);//定义布局页面的user类

        mIPersoninfoAPresenter = new PersoninfoAPresenterImpl(this);//创建个P层实例

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {//radiogroup单选
                if (checkedId == R.id.mail) {//男
                    localUser.setSex(1);
                } else if (checkedId == R.id.female) {//女
                    localUser.setSex(0);
                }
            }
        });

        binding.save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {  //从页面获取信息，并给localuser赋值
                localUser.setUserName(binding.name.getText().toString());
                localUser.setNumber(binding.phonenum.getText().toString());
                localUser.setCity(binding.city.getText().toString());
                localUser.setInterest(binding.hobby.getText().toString());
                localUser.setPassword(binding.password.getText().toString());

                mIPersoninfoAPresenter.save(localUser,photoFile);

            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(PersoninfoActivity.this, PersonalActivity.class);//点击back按钮后切换页面
                startActivity(intent2);//返回用户页面
            }
        });

        binding.upHeadPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersoninfoActivity.this, PhotoLayoutActivity.class);//点击头像上传按钮后，进入图像处理子页面
                startActivityForResult(intent, UP_LOAD_PICTURE);//切换成上传照片页面
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {//接受图像处理子页面返回结果
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//返回成功
            if (requestCode == UP_LOAD_PICTURE) {//从采集页获取图像
                //返回图片结果
                photoUri = data.getData();
                binding.head.setImageURI(photoUri);

//                photoPath=StringUtil.getRealPathFromUri(this,photoUri);
                //ZoomBitmap.verifyStoragePermissions(this);//权限申请，android10就是麻烦
//                photoFile= new File(photoPath);


            }
        }
    }


    @Override
    public <T> T request(int requestFlag) {
        return null;
    }

    @Override
    public <T> void response(T response, int responseFlag) {//接受P层结果
        if (responseFlag == IPersoninfoAView.RESPONSE_ONE) {

            localUser.setHead((String)response);
            String json = new Gson().toJson(localUser);
            SPUtil.put(this,"User",json);//保存带有图片URL的类

            Intent intent = new Intent(this, PersonalActivity.class);
            startActivity(intent);

            Looper.prepare();
            showToast("保存成功");
            Looper.loop();
        }
        else if(responseFlag == IPersoninfoAView.RESPONSE_TWO) {
            Looper.prepare();
            showToast((String) response);
            Looper.loop();

            Intent intent = new Intent(this, PersonalActivity.class);
            startActivity(intent);
        }else{
            Looper.prepare();
            showToast((String) response);
            Looper.loop();
        }

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}





