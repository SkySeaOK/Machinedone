package com.example.develop.machinedone.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.develop.machinedone.R;

public class CreateQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private PopupWindow question_popupWindow;
    private PopupWindow level_popupWindow;
    private LinearLayout linner_question;
    private LinearLayout linner_level;
    private LinearLayout red_defect;
    private LinearLayout orange_improve;
    private LinearLayout blue_task;
    private LinearLayout green_demand;
    private LinearLayout red_urgency;
    private LinearLayout orange_higher;
    private LinearLayout blue_medium;
    private LinearLayout green_low;
    private Button choose_btn;
    private TextView question_color;
    private TextView level_color;
    private TextView question_typeText;
    private TextView priority_levelText;
    boolean x = false;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果

    private ImageView iv_image;

    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    String[] items = new String[]{
            "拍照上传", "从相册中选择"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_question);
        ImageView toolbarImg = findViewById(R.id.toolbar_img);
        ImageView toolbarBack = findViewById(R.id.toolbar_back);
        TextView username = findViewById(R.id.toolbar_username);
        View contentView = LayoutInflater.from(CreateQuestionActivity.this).inflate(R.layout.question_type, null);
        linner_question = findViewById(R.id.question_type);
        linner_question.setOnClickListener(this);
        linner_level = findViewById(R.id.priority_level);
        linner_level.setOnClickListener(this);
        toolbarImg.setImageResource(R.mipmap.ic_launcher);
        toolbarBack.setImageResource(R.mipmap.ic_title_back);
        username.setText("User");
        toolbarImg.setOnClickListener(this);
        toolbarBack.setOnClickListener(this);
        TextView toolbarTitle = findViewById(R.id.title_toolbar);
        toolbarTitle.setText(R.string.toolbar_title2);
        question_color = findViewById(R.id.question_color);
        level_color = findViewById(R.id.level_color);
        question_typeText = findViewById(R.id.question_typeText);
        priority_levelText = findViewById(R.id.priority_levelText);
        choose_btn = findViewById(R.id.choose_btn);
        choose_btn.setOnClickListener(this);
        this.iv_image = (ImageView) this.findViewById(R.id.iv_image);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 返回按钮
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.question_type:
                if (x) {
                    question_popupWindow.dismiss();
                    x = false;
                } else {
                    showPopupWindow();
                    x = true;
                }
                break;
            case R.id.priority_level:
                if (x) {
                    level_popupWindow.dismiss();
                    x = false;
                } else {
                    prioritylevel();
                    x = true;
                }
                break;
            case R.id.red_defect:
                question_typeText.setText("缺陷");
                question_color.setBackgroundColor(Color.parseColor("#FF342E"));
                question_popupWindow.dismiss();
                break;
            case R.id.orange_improve:
                question_typeText.setText("改进");
                question_color.setBackgroundColor(Color.parseColor("#FFAE25"));
                question_popupWindow.dismiss();
                break;
            case R.id.blue_task:
                question_typeText.setText("任务");
                question_color.setBackgroundColor(Color.parseColor("#27C1FF"));
                question_popupWindow.dismiss();
                break;
            case R.id.green_demand:
                question_typeText.setText("需求");
                question_color.setBackgroundColor(Color.parseColor("#91FF19"));
                question_popupWindow.dismiss();
                break;
            case R.id.red_urgency:
                priority_levelText.setText("急");
                level_color.setBackgroundColor(Color.parseColor("#FF342E"));
                level_popupWindow.dismiss();
                break;
            case R.id.orange_higher:
                priority_levelText.setText("高");
                level_color.setBackgroundColor(Color.parseColor("#FFAE25"));
                level_popupWindow.dismiss();
                break;
            case R.id.blue_medium:
                priority_levelText.setText("中");
                level_color.setBackgroundColor(Color.parseColor("#27C1FF"));
                level_popupWindow.dismiss();
                break;
            case R.id.green_low:
                priority_levelText.setText("低");
                level_color.setBackgroundColor(Color.parseColor("#91FF19"));
                level_popupWindow.dismiss();
                break;
            case R.id.choose_btn:
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                builder.setTitle("上传图片");
                builder.setIcon(R.mipmap.ic_camera);
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        if (items[which] == "从相册中选择") {
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                            startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                        } else if (items[which] == "拍照上传") {
                            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                            // 判断存储卡是否可以用，可用进行存储
                            if (hasSdcard()) {
                                tempFile = new File(Environment.getExternalStorageDirectory(),
                                        PHOTO_FILE_NAME);
                                // 从文件中创建uri
                                Uri uri = Uri.fromFile(tempFile);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                            }
                            // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
                            startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
                        }

                    }

                });
                //setPositiveButton(builder);
                builder = setNegativeButton(builder);

                android.app.AlertDialog simplelistdialog = builder.create();
                simplelistdialog.show();
        }

    }

    /*
     * 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /*
     * 判断sdcard是否被挂载
     */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(CreateQuestionActivity.this, "未找到存储卡，无法存储照片！", 0).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                this.iv_image.setImageBitmap(bitmap);
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private android.app.AlertDialog.Builder setNegativeButton(android.app.AlertDialog.Builder builder) {
        // TODO Auto-generated method stub
        return builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(CreateQuestionActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(CreateQuestionActivity.this).inflate(R.layout.question_type, null);
        red_defect = contentView.findViewById(R.id.red_defect);
        orange_improve = contentView.findViewById(R.id.orange_improve);
        blue_task = contentView.findViewById(R.id.blue_task);
        green_demand = contentView.findViewById(R.id.green_demand);
        red_defect.setOnClickListener(this);
        orange_improve.setOnClickListener(this);
        blue_task.setOnClickListener(this);
        green_demand.setOnClickListener(this);
        question_popupWindow = new PopupWindow(contentView, 400, 0);
        question_popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        question_popupWindow.setOutsideTouchable(true);
        question_popupWindow.showAsDropDown(linner_question, 280, 0);
        question_popupWindow.setFocusable(true);

    }

    private void prioritylevel() {
        View contentView = LayoutInflater.from(CreateQuestionActivity.this).inflate(R.layout.priority_level, null);
        red_urgency = contentView.findViewById(R.id.red_urgency);
        orange_higher = contentView.findViewById(R.id.orange_higher);
        blue_medium = contentView.findViewById(R.id.blue_medium);
        green_low = contentView.findViewById(R.id.green_low);
        red_urgency.setOnClickListener(this);
        orange_higher.setOnClickListener(this);
        blue_medium.setOnClickListener(this);
        green_low.setOnClickListener(this);
        level_popupWindow = new PopupWindow(contentView, 400, 0);
        level_popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        level_popupWindow.setOutsideTouchable(true);
        level_popupWindow.showAsDropDown(linner_level, 280, 0);
        level_popupWindow.setFocusable(true);
    }
}
