package com.bnuz.outdooractivitymanagementsystem.ToolUtils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
    /**
     * 获取当前时间
     *
     */
    public static Date getDate(String str) {
        try {
            @SuppressLint("SimpleDateFormat") java.text.SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy/MM/dd");
            Date date = formatter.parse(str);
            return date;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;

    }


    public static Bitmap returnBitMap(final String url){
        final Bitmap[] bitmap = new Bitmap[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection)imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap[0] = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return bitmap[0];
    }


}
