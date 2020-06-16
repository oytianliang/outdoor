package com.bnuz.outdooractivitymanagementsystem.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.bean.ActivityListView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ActivityAdapter extends ArrayAdapter<ActivityListView> {

    private int resourceId;

    public ActivityAdapter(Context context, int textViewResourceId, List<ActivityListView> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        ActivityListView alv = getItem(position);
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView activity_photo = (ImageView) view.findViewById(R.id.activity_photo);
        TextView activity_name = (TextView) view.findViewById(R.id.activity_name);
        TextView activity_describe=(TextView) view.findViewById(R.id.activity_describe);
        assert alv != null;
        activity_photo.setImageResource(alv.getImageId());
        activity_name.setText(alv.getName());
        activity_describe.setText(alv.getDescribe());
        return view;
    }
}