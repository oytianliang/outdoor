package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bnuz.outdooractivitymanagementsystem.Presenter.impl.CreateAPresenterImpl;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.ICreateAPresenter;
import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.DataUtil;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.SPUtil;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.StringUtil;
import com.bnuz.outdooractivitymanagementsystem.View.inter.ICreateAView;
import com.bnuz.outdooractivitymanagementsystem.bean.Activity;
import com.bnuz.outdooractivitymanagementsystem.bean.User;
import com.google.gson.Gson;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener,ICreateAView {

    private ICreateAPresenter mICreateAPresenter;
    private ImageView mpic;
    String photoPath;
    Uri photoUri;
    File photoFile;


    private static final int UP_LOAD_PICTURE = 1;

    private Button button_back;
    private Button button_confirm;
    private Button button_upImage;
    private Button button_startTime;
    private Button button_endTime;
    private Button button_deadline;

    private TextView startTime;
    private TextView endTime;
    private TextView deadline;
    private Date start;
    private Date end;
    private Date dead;//日期格式时间



    private EditText activity_name;
    private EditText activity_describe;
    private EditText activity_location;

    Calendar calendar;
    Calendar calendar2;
    Calendar calendar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mICreateAPresenter = new CreateAPresenterImpl(this);
        setContentView(R.layout.activity_create);

        //初始化控件
        button_back = (Button) findViewById(R.id.返回btn);
        button_confirm = (Button) findViewById(R.id.确定btn);
        button_upImage = (Button) findViewById(R.id.添加图片btn);
        button_deadline = (Button) findViewById(R.id.截止时间btn);
        button_startTime = (Button) findViewById(R.id.开始时间btn);
        button_endTime = (Button) findViewById(R.id.结束时间btn);
        mpic = (ImageView) findViewById(R.id.活动封面);
        deadline = (TextView) findViewById(R.id.截止时间);
        startTime = (TextView) findViewById(R.id.活动开始时间);
        endTime = (TextView) findViewById(R.id.活动结束时间);
        activity_name = (EditText) findViewById(R.id.活动名称);
        activity_describe = (EditText) findViewById(R.id.活动形式);
        activity_location = (EditText) findViewById(R.id.活动地点);

        button_back.setOnClickListener(this);
        button_confirm.setOnClickListener(this);
        button_upImage.setOnClickListener(this);
        button_deadline.setOnClickListener(this);
        button_startTime.setOnClickListener(this);
        button_endTime.setOnClickListener(this);

        mpic.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //这里写按钮逻辑
            case R.id.返回btn:
                Intent intent1 = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intent1);//切换成菜单页面
                break;
            case R.id.确定btn:
                String json= (String) SPUtil.get(CreateActivity.this,"User","1");
                User user=new Gson().fromJson(json,User.class);//获取本地用户类
                Activity activity = new Activity(//初始化
                        user.getUserId(),
                        activity_name.getText().toString(),
                        activity_describe.getText().toString(),
                        start,end,dead,
                        activity_location.getText().toString()
                );

                saveActivity(activity);//先提前把数据保存一次
                mICreateAPresenter.LogicCheck(activity,photoFile);
                break;
            case R.id.添加图片btn:
                Intent intent = new Intent(this, PhotoLayoutActivity.class);
                startActivityForResult(intent,UP_LOAD_PICTURE);//切换成上传照片页面
                break;
            case R.id.开始时间btn://开始时间
                calendar=Calendar.getInstance();
                new DatePickerDialog( this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String text = year + "/" + (month + 1) + "/" + dayOfMonth;
                        Toast.makeText( CreateActivity.this, text, Toast.LENGTH_SHORT ).show();
                        startTime.setText(text);
                    }
                }
                        ,calendar.get(Calendar.YEAR)
                        ,calendar.get(Calendar.MONTH)
                        ,calendar.get(Calendar.DAY_OF_MONTH)).show();
                start = DataUtil.getDate(startTime.getText().toString());
                break;
            case R.id.结束时间btn://结束时间
                calendar2=Calendar.getInstance();
                new DatePickerDialog( this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String text = year + "/" + (month + 1) + "/" + dayOfMonth;
                        Toast.makeText( CreateActivity.this, text, Toast.LENGTH_SHORT ).show();
                        endTime.setText(text);    }
                }
                        ,calendar2.get(Calendar.YEAR)
                        ,calendar2.get(Calendar.MONTH)
                        ,calendar2.get(Calendar.DAY_OF_MONTH)).show();
                end = DataUtil.getDate(endTime.getText().toString());
                break;
            case R.id.截止时间btn://截至报名时间
                calendar3=Calendar.getInstance();
                new DatePickerDialog( this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String text = year + "/" + (month + 1) + "/" + dayOfMonth;
                        Toast.makeText( CreateActivity.this, text, Toast.LENGTH_SHORT ).show();
                        deadline.setText(text);    }
                }
                        ,calendar3.get(Calendar.YEAR)
                        ,calendar3.get(Calendar.MONTH)
                        ,calendar3.get(Calendar.DAY_OF_MONTH)).show();
                dead = DataUtil.getDate(deadline.getText().toString());
                break;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//返回成功
            if (requestCode == UP_LOAD_PICTURE) {
                //从photoActivity拿到的URI
                photoUri = data.getData();
                mpic.setImageURI(photoUri);
                photoPath = StringUtil.getRealPathFromUri(this, photoUri);
                photoFile=new File(photoPath);


            }
        }
    }

    @Override
    public <T> T request(int requestFlag) {
        return null;
    }

    @Override
    public <T> void response(T response, int responseFlag) {

        if(responseFlag==ICreateAView.RESPONSE_ONE){

            String json = (String) SPUtil.get(CreateActivity.this, "Activity", "1");
            Activity activity2 = new Gson().fromJson(json, Activity.class);//获取本地用户类

            activity2.setActivityShow((String)response);

            json = new Gson().toJson(activity2);//转换成String
            SPUtil.put(this,"Activity",json);//保存包含url的活动类

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);//切换成主页面

            Looper.prepare();
            showToast("保存成功");
            Looper.loop();



        }else if(responseFlag==ICreateAView.RESPONSE_TWO){ showToast((String)response);

        }else{ showToast((String)response); }

    }

    @Override
    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public <T> void saveActivity(Activity activity) {
        String json = new Gson().toJson(activity);//转换成String
        SPUtil.put(this,"Activity",json);//存入share_data;
    }
}
