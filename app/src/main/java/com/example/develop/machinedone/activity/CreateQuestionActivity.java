package com.example.develop.machinedone.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import com.example.develop.machinedone.R;

import android.widget.AdapterView;
import android.widget.GridView;

import com.example.develop.machinedone.adapter.GvAdapter;
import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;


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
    private TextView question_color;
    private TextView level_color;
    private TextView question_typeText;
    private TextView priority_levelText;
    boolean x = false;
    boolean y = false;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果

    private GridView gridView;
    private GvAdapter adapter;
    private List<String> list;
    ImagePicker imagePicker ;
    String path;
    private ImageView img;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_question);
        ImageView toolbarImg = findViewById(R.id.toolbar_img);
        ImageView toolbarBack = findViewById(R.id.toolbar_back);
        TextView username = findViewById(R.id.toolbar_username);
        View inflate = LayoutInflater.from(this).inflate(R.layout.phone_item, null, false);
        img = inflate.findViewById(R.id.img);
        View contentView = LayoutInflater.from(CreateQuestionActivity.this).inflate(R.layout.question_type, null);
        linner_question = findViewById(R.id.question_type);
        linner_question.setOnClickListener(this);
        linner_level = findViewById(R.id.priority_level);
        linner_level.setOnClickListener(this);
        toolbarImg.setImageResource(R.mipmap.title_img);
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
        gridView = (GridView) findViewById(R.id.grid_view);
        initView();
    }

    private void initView() {
        imagePicker = new ImagePicker();
        // 设置标题
        imagePicker.setTitle("添加图片");
        // 设置是否裁剪图片
        imagePicker.setCropImage(true);
        list = new ArrayList<>();
        adapter = new GvAdapter(this, list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView
                .OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View
                    view, int position, long id) {
                //判断是否是最后一个。
                if (position==parent.getChildCount()-1){
                    if (position==6){//不能点击了
                    }else{
                        openPhoto();
                    }
                }else
                    {//可以加点预览功能。
                        Intent intent = new Intent(CreateQuestionActivity.this, ImageDetail.class);
                        intent.putExtra("key",list.get(position));
                        startActivity(intent);
                        }

            }
        });
    }
    public void openPhoto() {
        // 启动图片选择器
        imagePicker.startChooser(this, new ImagePicker.Callback() {
            // 选择图片回调
            @Override
            public void onPickImage(Uri imageUri) {
            }
            // 裁剪图片回调
            @Override
            public void onCropImage(Uri imageUri) {
                if (list.size()>=6){
                    Toast.makeText(CreateQuestionActivity.this,"最多选择六张图片",Toast.LENGTH_LONG).show();
                }else{
                    list.add(String.valueOf(imageUri));
                }
                adapter.notifyDataSetChanged();
//                path= String.valueOf(imageUri);
            }

            // 自定义裁剪配置
            @Override
            public void cropConfig(CropImage.ActivityBuilder
                                           builder) {
                builder
                        // 是否启动多点触摸
                        .setMultiTouchEnabled(false)
                        // 设置网格显示模式
                        .setGuidelines(CropImageView.Guidelines.OFF)
                        // 圆形/矩形
                        .setCropShape(CropImageView.CropShape
                                .RECTANGLE)
                        // 调整裁剪后的图片最终大小
                        .setRequestedSize(720, 960)
                        // 宽高比
                        .setAspectRatio(12, 16);
            }
            // 用户拒绝授权回调
            @Override
            public void onPermissionDenied(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int
            resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.onActivityResult(CreateQuestionActivity.this,requestCode, resultCode, data);
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
                    showPopupWindow(view);
                    x = true;
                }
                break;
            case R.id.priority_level:
                if (y) {
                    level_popupWindow.dismiss();
                    y = false;
                } else {
                    prioritylevel();
                    y = true;
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
        }
    }

    private void showPopupWindow(View v)
    {
        View contentView = LayoutInflater.from(CreateQuestionActivity.this).inflate(R.layout.question_type, null);
        red_defect = contentView.findViewById(R.id.red_defect);
        orange_improve = contentView.findViewById(R.id.orange_improve);
        blue_task = contentView.findViewById(R.id.blue_task);
        green_demand = contentView.findViewById(R.id.green_demand);
        red_defect.setOnClickListener(this);
        orange_improve.setOnClickListener(this);
        blue_task.setOnClickListener(this);
        green_demand.setOnClickListener(this);
        int[] location = new int[2];
        linner_question.getLocationOnScreen(location);
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
