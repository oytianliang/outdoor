package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bnuz.outdooractivitymanagementsystem.Presenter.impl.NodeToViewAPresenterImpl;
import com.bnuz.outdooractivitymanagementsystem.Presenter.inter.INodeAPresenter;
import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.View.inter.INodeToViewAView;
public class NodeToViewActivity extends AppCompatActivity implements INodeToViewAView {

    private INodeAPresenter mINodeToViewAPresenter;
    private TextView text_activityName;
    //以下节点任务，可考虑重复创建，并放在列表中
    private TextView text_node1;
    private TextView text_node2;
    private TextView text_node3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mINodeToViewAPresenter = new NodeToViewAPresenterImpl(this);
        setContentView(R.layout.activity_node_to_view);

        //这里写相关代码，传递用户信息？访问节点任务信息
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
