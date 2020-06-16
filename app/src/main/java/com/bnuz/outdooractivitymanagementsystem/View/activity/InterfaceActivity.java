package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bnuz.outdooractivitymanagementsystem.Adapter.ActivityAdapter;
import com.bnuz.outdooractivitymanagementsystem.Adapter.ImageAdapter;
import com.bnuz.outdooractivitymanagementsystem.Presenter.impl.InterfaceAPresenterImpl;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IInterfaceAPresenter;
import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IInterfaceAView;
import com.bnuz.outdooractivitymanagementsystem.bean.ActivityListView;
import com.bnuz.outdooractivitymanagementsystem.bean.DataBean;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.listener.OnPageChangeListener;
import com.youth.banner.util.BannerUtils;
import com.youth.banner.util.LogUtils;

import java.util.ArrayList;
import java.util.List;


public class InterfaceActivity extends AppCompatActivity implements View.OnClickListener, IInterfaceAView , OnPageChangeListener {

    private Button search;
    private IInterfaceAPresenter mIInterfaceAPresenter;
    private SwipeRefreshLayout refresh;
    private Banner banner;
    private List<ActivityListView> activityList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIInterfaceAPresenter = new InterfaceAPresenterImpl(this);
        setContentView(R.layout.activity_interface);

        ImageAdapter adapter = new ImageAdapter(DataBean.getTestData());//轮播图片适配器初始化

        initActivity(); // 初始化活动数据
        ActivityAdapter activityadapter = new ActivityAdapter(InterfaceActivity.this, R.layout.activitylistview, activityList);//初始化活动适配器
        ListView listView = (ListView) findViewById(R.id.listview);//初始化控件
        listView.setAdapter(activityadapter);

        //初始化控件
        banner= findViewById(R.id.banner);
        refresh= (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        RoundLinesIndicator indicator = (RoundLinesIndicator) findViewById(R.id.indicator);

        ImageButton calendarButton = (ImageButton) findViewById(R.id.imageButton_calendar);
        Button mapButton = (Button) findViewById(R.id.button_location);
        search=(Button) findViewById(R.id.button_search);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent1 = new Intent(InterfaceActivity.this, InterfaceActivity.class);
                        startActivity(intent1);//切换成主页面
                        break;
                    case R.id.center:
                        Intent intent2 = new Intent(InterfaceActivity.this, MainActivity.class);
                        startActivity(intent2);//切换成中心页面
                        break;
                    case R.id.explore:
                        Intent intent3 = new Intent(InterfaceActivity.this, FindActivity.class);
                        startActivity(intent3);//切换成发现页面
                        break;
                    case R.id.mail:
                        Intent intent4 = new Intent(InterfaceActivity.this, NewsActivity.class);
                        startActivity(intent4);//切换成消息页面
                        break;
                    case R.id.self:
                        Intent intent5 = new Intent(InterfaceActivity.this, PersonalActivity.class);
                        startActivity(intent5);//切换成个人页面
                        break;
                }
                return true;
            }
        });

        calendarButton.setOnClickListener(this);
        mapButton.setOnClickListener(this);
        search.setOnClickListener(this);

        banner.setAdapter(adapter)//设置适配器
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setBannerRound(BannerUtils.dp2px(5))//圆角
                .setIndicator(new CircleIndicator(this))//设置指示器
                .addOnPageChangeListener(this)//添加切换监听
                .setOnBannerListener((data, position) -> {
                    Snackbar.make(banner, ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                    LogUtils.d("position：" + position);
                });//设置点击事件,传this也行

        //和下拉刷新配套使用
        refresh.setOnRefreshListener(() -> {
            //模拟网络请求需要3秒，请求完成，设置setRefreshing 为false
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    refresh.setRefreshing(false);
                    banner.setDatas(DataBean.getTestData2());
                }
            }, 3000);
        });

    }

    private void initActivity(){
        //这里在列表中插入活动数据
        ActivityListView activity1 = new ActivityListView("北师珠出游","这是计算机班级得旅游",R.drawable.image1);
        activityList.add(activity1);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        LogUtils.d("onPageSelected:" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton_calendar:
                break;
            case R.id.button_location:
                break;
            case R.id.button_search:
                mIInterfaceAPresenter.searchManagement();//搜索活动
                /*这里差个搜索界面*/
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

    public String getSearchText(){ return search.getText().toString(); }

    @Override
    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
