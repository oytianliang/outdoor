package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.bnuz.outdooractivitymanagementsystem.bean.Activity;
import com.bnuz.outdooractivitymanagementsystem.Presenter.impl.ManagementAPresenterImpl;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IManagementAPresenter;
import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IManagementAView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ManagementActivity extends AppCompatActivity implements View.OnClickListener,IManagementAView {

    private IManagementAPresenter mIManagementAPresenter;
    private Button button_manage;
    private Button button_join;
    private ImageView ACTPhoto;
    private TextView title;
    private TextView activityid;
    private TextView time;
    private TextView location;
    private TextView owner;
    int ActId=0;
    protected static final int CHANGE_UI = 1;
    protected static final int ERROR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIManagementAPresenter = new ManagementAPresenterImpl(this);
        setContentView(R.layout.activity_management);

        button_manage = findViewById(R.id.manageAct);
        button_join = findViewById(R.id.button12);
        ACTPhoto = (ImageView) findViewById(R.id.活动封面);
        title = (TextView) findViewById(R.id.title);
        activityid = (TextView) findViewById(R.id.activityid);
        time = (TextView) findViewById(R.id.time);
        location = (TextView) findViewById(R.id.location);
        owner = (TextView) findViewById(R.id.owner);

        button_manage.setOnClickListener(this);
        button_join.setOnClickListener(this);

        Intent intent;
        if((intent = getIntent())!= null){
            ActId = intent.getIntExtra("actid",1);
        }else{
            System.out.println("don't get actid");
            ActId= 1;
        }
//        try{
//            intent = getIntent();
//            ActId = intent.getIntExtra("actid",1);
//        }catch(Exception e){
//            System.out.println("don't get actid");
//            ActId= 1;
//        }
        //Intent intent = getIntent();//获取跳转该页面传过来的活动ID  //页面跳转报错
        //ActId = 1;
        mIManagementAPresenter.getActivity(ActId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.manageAct:
                Intent intent = new Intent(ManagementActivity.this,ManagementActivity2.class);
                startActivity(intent);//切换成management2
                //这里写按钮逻辑
                break;
            case R.id.button12:
                break;
        }
    }

    @Override
    public <T> T request(int requestFlag) {
        return null;
    }

    @Override
    public <T> void response(T response, int responseFlag) {
        if(responseFlag == IManagementAView.RESPONSE_ONE){
            final Activity activity = (Activity)response;

            //showphoto("activity.head");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    title.setText(activity.getActivityTitle());
                    //title.setText(String.valueOf(ActId)); //test
                    activityid.setText("活动ID:" + activity.getActivityId());
                    time.setText("时间:"+activity.getBegin().toString()+"————"+activity.getEnd().toString());
                    location.setText("地点:"+activity.getLocate());
                    owner.setText("所有者:"+activity.getOwner());
                    showphoto("activity.head");

//                    final String path = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1591099408&di=c4524158d89c02e303b17329c9a3c011&src=http://a3.att.hudong.com/14/75/01300000164186121366756803686.jpg";
//                    Uri photoUri = Uri.parse(path);
//                    ACTPhoto.setImageURI(photoUri);
                }
            });
//            final String path = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1591099408&di=c4524158d89c02e303b17329c9a3c011&src=http://a3.att.hudong.com/14/75/01300000164186121366756803686.jpg";
//            Uri photoUri = Uri.parse(path);
//            ACTPhoto.setImageURI(photoUri);
        }
    }
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == CHANGE_UI) {
                Bitmap bitmap = (Bitmap) msg.obj;
                ACTPhoto.setImageBitmap(bitmap);
            } else if (msg.what == ERROR) {
                Toast.makeText(ManagementActivity.this, "显示图片错误",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void showphoto(final String uurl){
        //final String path = uurl.toString().trim();
        final String path = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1591099408&di=c4524158d89c02e303b17329c9a3c011&src=http://a3.att.hudong.com/14/75/01300000164186121366756803686.jpg";
        if (TextUtils.isEmpty(path)) {
            Toast.makeText(this, "图片路径不能为空", Toast.LENGTH_SHORT).show();
        } else {

            new Thread() {
                private HttpURLConnection conn;
                private Bitmap bitmap;
                public void run() {
                    try {
                        URL url = new URL(path);
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(5000);
                        int code = conn.getResponseCode();
                        if (code == 200) {
                            InputStream is = conn.getInputStream();
                            bitmap = BitmapFactory.decodeStream(is);
                            Message msg = new Message();
                            msg.what = CHANGE_UI;
                            msg.obj = bitmap;
                            handler.sendMessage(msg);
                        } else {
                            Message msg = new Message();
                            msg.what = ERROR;
                            handler.sendMessage(msg);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Message msg = new Message();
                        msg.what = ERROR;
                        handler.sendMessage(msg);
                    }
                    conn.disconnect();
                }
            }.start();
        }
    }

    @Override
    public <T> void showToast(String msg) {

    }


}
