package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bnuz.outdooractivitymanagementsystem.Presenter.impl.Management2APresenterImpl;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.IManagement2APresenter;
import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.View.inter.IManagement2AView;
import com.bnuz.outdooractivitymanagementsystem.bean.Activity;
import com.bnuz.outdooractivitymanagementsystem.bean.User;

import java.util.ArrayList;
import java.util.List;


public class ManagementActivity2 extends AppCompatActivity implements View.OnClickListener,IManagement2AView {

    private IManagement2APresenter mIManagement2APresenter;
    private TextView title;
    private TextView time;
    private TextView location;
    private ListView mListView;
    private List<String> list = new ArrayList<String>();
    int ActId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIManagement2APresenter = new Management2APresenterImpl(this);
        setContentView(R.layout.activity_management2);


        title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time);
        location = (TextView) findViewById(R.id.location);
        //初始化ListView控件
        mListView = (ListView) findViewById(R.id.lv);


        Intent intent;
        if((intent = getIntent())!= null){
            ActId = intent.getIntExtra("actid",1);
        }else{
            System.out.println("don't get actid");
            ActId= 1;
        }
        mIManagement2APresenter.getACTByACTID(ActId);
        mIManagement2APresenter.getUserByACTId(ActId);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.manageAct:
//                //这里写按钮逻辑button_manage
//                break;
//            case R.id.button12:
//                //这里写按钮逻辑button_join
//                break;
//        }
    }

    @Override
    public <T> T request(int requestFlag) {
        return null;
    }

    @Override
    public <T> void response(T response, final int responseFlag) {
        if(responseFlag == IManagement2AView.RESPONSE_ONE) {  //成功
            final Activity activity = (Activity)response;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    title.setText(activity.getActivityTitle());
                    time.setText("时间:" + activity.getBegin().toString() + "————" + activity.getEnd().toString());
                    location.setText("地点:" + activity.getLocate());
                }
            });

        }
        if(responseFlag == IManagement2AView.RESPONSE_TWO){//成功
            final User[] users = (User[]) response;
            if(users == null){
                System.out.println("don't get users");
            }else {
                for (User user:users) {
                    list.add(user.getUserName());
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MyBaseAdapter mAdapter = new MyBaseAdapter();
                    mListView.setAdapter(mAdapter);
                }
            });

        }else{                                    //失败

        }
    }

    @Override
    public <T> void showToast(String msg) {

    }
    class MyBaseAdapter extends BaseAdapter {
        //得到item的总数
        String[] names = new String[list.size()] ;
        void init() {
            for (int i = 0; i < list.size(); i++) {
                names[i] = list.get(i);
            }
        }

        @Override
        public int getCount() {
            //返回ListView Item条目的总数
            return names.length;
        }
        //得到Item代表的对象
        @Override
        public Object getItem(int position) {
            //返回ListView Item条目代表的对象
            return names[position];
        }
        //得到Item的id
        @Override
        public long getItemId(int position) {
            //返回ListView Item的id
            return position;
        }
        //得到Item的View视图
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            init();
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).
                        inflate(R.layout.list_item,parent,false);
                holder = new ViewHolder();
                holder.mTextView = (TextView)convertView.findViewById
                        (R.id. item_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.mTextView.setText(names[position]);
            return convertView;
        }
        class ViewHolder {
            TextView mTextView;
            ImageView imageView;
        }


    }
}
