package com.bnuz.outdooractivitymanagementsystem.View.activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;

import com.bnuz.outdooractivitymanagementsystem.R;
import com.bnuz.outdooractivitymanagementsystem.ToolUtils.StringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PhotoLayoutActivity extends AppCompatActivity{

    private static final int CAMERA_REQUEST_CODE = 1;// 拍照的requestCode
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PERMISSION_CAMERA_REQUEST_CODE = 3; // 申请相机权限的requestCode
    private static final int CUT_CAMERA_PICTURE=4;//裁剪
    private static final int CUT_PHOTO_PICTURE=5;
    ImageView imageShow;
    TextView textName;
    Button butPai;
    Button butXian;
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";/* 相片名称 */
    private File photoFile;
    private Uri mCameraUri;//用于保存拍照图片的uri
    private Uri mPhotoUri;//保存相册图片的URI；
    private String mCameraImagePath;// 用于保存图片的文件路径，Android 10以下使用图片路径访问图片
    private String mGalleryPath;
    private boolean isAndroidQ = Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q;// 是否是Android 10以上手机



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_up_laout);

        imageShow =(ImageView) findViewById(R.id.image_show);
        textName =(TextView)findViewById(R.id.text_name);
        butPai=(Button)findViewById(R.id.拍照);
        butXian=(Button)findViewById(R.id.相册);

        butPai.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkPermissionAndCamera();
            }
        });
        butXian.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK);
                pickPhotoIntent.setType("image/*");
                startActivityForResult(pickPhotoIntent, PHOTO_REQUEST_GALLERY);// 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
            }
        });
    }

    /**
     * 检查权限并拍照。
     * 调用相机前先检查权限。
     */
    private void checkPermissionAndCamera() {
        int hasCameraPermission = ContextCompat.checkSelfPermission(getApplication(),
                Manifest.permission.CAMERA);
        if (hasCameraPermission == PackageManager.PERMISSION_GRANTED) {//有调起相机拍照。
            openCamera();
        } else {//没有权限，申请权限。
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    PERMISSION_CAMERA_REQUEST_CODE);
        }
    }

    /**
     * 调起相机拍照
     */
    private void openCamera() {
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断是否有相机
        if (captureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            Uri photoUri = null;

            if (isAndroidQ) {
                // 适配android 10
                photoUri = createImageUri();
            } else {
                try {
                    photoFile = createImageFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (photoFile != null) {
                    mCameraImagePath = photoFile.getAbsolutePath();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
                        photoUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", photoFile);
                    } else {
                        photoUri = Uri.fromFile(photoFile);
                    }
                }
            }

            mCameraUri = photoUri;
            if (photoUri != null) {
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);//开启Intent处理拍照过程
                captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                startActivityForResult(captureIntent,CAMERA_REQUEST_CODE);
            }
        }
    }

    /**
     * 创建图片地址uri,用于保存拍照后的照片 Android 10以后使用这种方法
     */
    private Uri createImageUri() {
        String status = Environment.getExternalStorageState();
        // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        } else {
            return getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, new ContentValues());
        }
    }

    /**
     * 创建保存图片的文件
     */
    private File createImageFile() throws IOException {
        String imageName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (!storageDir.exists()) {
            storageDir.mkdir();
        }
        File tempFile = new File(storageDir, imageName);
        if (!Environment.MEDIA_MOUNTED.equals(EnvironmentCompat.getStorageState(tempFile))) {
            return null;
        }
        return tempFile;
    }

    /**
     * 处理权限申请的回调。
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //允许权限，有调起相机拍照。
                openCamera();
            } else {
                //拒绝权限，弹出提示框。
                Toast.makeText(this, "拍照权限被拒绝", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void startPhotoZoom2(Uri uri) {// 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        mPhotoUri = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory()+ File.separator + "Android" + "/icon_temp1.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoFile);

        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);

        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 110);
        intent.putExtra("outputY", 110);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CUT_PHOTO_PICTURE);


    }

    /**
     * 调用系统裁剪的方法
     */
    private void startPhoneZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        //是否可裁剪
        intent.putExtra("corp", "true");
        //裁剪器高宽比
        intent.putExtra("aspectY", 1);
        intent.putExtra("aspectX", 1);
        //设置裁剪框高宽
        intent.putExtra("outputX", 110);
        intent.putExtra("outputY", 110);
        //  Uri temp =Uri.parse(cameraSavePath); //Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri temp =Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath()+ File.separator + "Android"+File.separator+"xx.jpg");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, temp);
        }


        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        //返回数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CUT_CAMERA_PICTURE);
    }

    private File saveimage(Bitmap bmp){
        File f = new File(Environment.getExternalStorageDirectory()
                , "qwert.jpg");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return f;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE&&resultCode == RESULT_OK) {//从相机返回数据
//            if (isAndroidQ) {
//                // Android 10 使用图片uri加载
//                imageShow.setImageURI(mCameraUri);
//
//            }else{
//                // 使用图片路径加载
//                imageShow.setImageBitmap(BitmapFactory.decodeFile(mCameraImagePath));
//
//            }
            mCameraImagePath = StringUtil.getRealPathFromUri(this, mCameraUri);
            photoFile= new File(mCameraImagePath);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //test.xxx.com.myapplication.fileprovider 是在清单文件配置的 android:authorities
                mPhotoUri = FileProvider.getUriForFile(this, "com.bnuz.outdooractivitymanagementsystem.FileProvider", photoFile);
            }else{
                mPhotoUri = Uri.fromFile(photoFile);
            }
                startPhoneZoom(mPhotoUri);
//                Intent intent = new Intent();//传回照片
//                intent.setData(mCameraUri);
//                setResult(RESULT_OK, intent);
//                finish();
        }else if (requestCode == PHOTO_REQUEST_GALLERY&&resultCode == RESULT_OK) { // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
//                mPhotoUri = data.getData();
//                imageShow.setImageURI(mPhotoUri);
                startPhotoZoom2(data.getData());
//                 mGalleryPath = StringUtil.getRealPathFromUri(this,mPhotoUri);
//                Intent intent = new Intent();//传回照片
//                intent.setData(mPhotoUri);
//                // 设置返回码和返回携带的数据
//                setResult(RESULT_OK, intent);
//                // RESULT_OK就是一个默认值，=-1，它说OK就OK吧
//                finish();
            }
        }else if(requestCode==CUT_CAMERA_PICTURE&&resultCode == RESULT_OK){//拍照图片裁剪结果
            Bitmap photo=null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                File file = null;
                Uri temp =Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath()+ File.separator + "Android"+File.separator+"xx.jpg");
                try {
                    file = new File(new URI(temp.toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                photo = BitmapFactory.decodeFile(file.toString());

            }else{
                File f =new File(mCameraImagePath);

                photo = BitmapFactory.decodeFile(f.toString());
            }
            File photoFile=saveimage(photo);
            if(photoFile!=null){
                mPhotoUri=Uri.parse(photoFile.toString());
                Intent intent = new Intent();//传回照片
                intent.setData(mPhotoUri);
                setResult(RESULT_OK, intent);
                finish();
            }


        }else if(requestCode==CUT_PHOTO_PICTURE&&resultCode == RESULT_OK){//选择图片的裁剪结果
            File file = null;
            try {
                file = new File(new URI(photoFile.toString()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            Bitmap photo = BitmapFactory.decodeFile(file.toString());
            File photoFile=saveimage(photo);
            if(photoFile!=null){
                mPhotoUri=Uri.parse(photoFile.toString());
                Intent intent = new Intent();//传回照片
                intent.setData(mPhotoUri);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

}

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PHOTO_REQUEST_GALLERY) {
//            // 从相册返回的数据
//            if (data != null) {
//                Uri uri = data.getData();//获取URI
//                imageShow.setImageURI(uri);
//
//                Intent intent=new Intent();// 将先要传回的数据放到Intent里
//                intent.setData(uri);
//                setResult(RESULT_OK, intent);// 设置返回码和返回携带的数据
//                finish();
//            }
//        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
//            // 从相机返回的数据
//            if (hasSdcard()) {
//                imageShow.setImageURI(Uri.fromFile(tempFile));
//                Uri uri = data.getData();//获取URI
//                //String path = StringUtil.getRealPathFromUri(this,Uri.fromFile(tempFile));
//
//                Intent intent=new Intent();// 将先要传回的数据放到Intent里
//                intent.setData(uri);
//                setResult(RESULT_OK, intent);// 设置返回码和返回携带的数据
//                finish();
//            } else {
//                Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
//            }
//        }
//        try {
//            // 将临时文件删除
//            tempFile.delete();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }






















