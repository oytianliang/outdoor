package com.bnuz.outdooractivitymanagementsystem.Adapter;


import android.view.ViewGroup;

import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.bean.DataBean;
import com.bnuz.outdooractivitymanagementsystem.viewholder.ImageTitleHolder;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;




/**
 * 自定义布局，图片+标题
 */

public class ImageTitleAdapter extends BannerAdapter<DataBean, ImageTitleHolder> {

    public ImageTitleAdapter(List<DataBean> mDatas) {
        super(mDatas);
    }

    @Override
    public ImageTitleHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ImageTitleHolder(BannerUtils.getView(parent, R.layout.activity_interface));
    }

    @Override
    public void onBindView(ImageTitleHolder holder, DataBean data, int position, int size) {
        holder.imageView.setImageResource(data.imageRes);
        holder.title.setText(data.title);
    }



}