package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.bnuz.outdooractivitymanagementsystem.Presenter.impl.ClockinAPresenterImpl;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IClockinAPresenter;
import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IClockinAView;

public class ClockinActivity extends AppCompatActivity implements View.OnClickListener,IClockinAView{

    private IClockinAPresenter mIClockinAPresenter;
    private Button clockin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIClockinAPresenter = new ClockinAPresenterImpl(this);
        setContentView(R.layout.activity_clockin);
        //初始化控件
        clockin = (Button) findViewById(R.id.button_clockin);


        clockin.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_clockin:

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
