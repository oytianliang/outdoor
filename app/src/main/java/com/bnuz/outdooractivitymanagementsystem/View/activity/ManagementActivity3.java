package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bnuz.outdooractivitymanagementsystem.Presenter.impl.Management3APresenterImpl;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IManagement3APresenter;
import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IManagement3AView;
public class ManagementActivity3 extends AppCompatActivity implements View.OnClickListener,IManagement3AView {

    private IManagement3APresenter mIManagement3APresenter;
    private Button button_delete;
    //这里是页面的文本控件，先暂时申明变量，还未绑定
    private TextView text_uesrname;
    private TextView text_task_completion;
    private TextView text_next_task;
    private TextView text_time_remain;
    private TextView text_node_name;
    private TextView text_node_completion;
    private TextView text_node_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIManagement3APresenter = new Management3APresenterImpl(this);
        setContentView(R.layout.activity_management3);

        button_delete = findViewById(R.id.button15);

        button_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button15:
                //这里写按钮逻辑
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
