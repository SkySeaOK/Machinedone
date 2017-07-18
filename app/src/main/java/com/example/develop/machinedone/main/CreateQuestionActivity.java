package com.example.develop.machinedone.main;

import android.content.Intent;
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

import com.example.develop.machinedone.R;

public class CreateQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private PopupWindow mpopupWindow;
    private LinearLayout linner;
//    private LinearLayout red;
//    private LinearLayout orange;
//    private LinearLayout blue;
//    private LinearLayout green;
//    private TextView question_typeText;
//    private TextView priority_levelText;
//    boolean x=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_question);
        ImageView toolbarImg = findViewById(R.id.toolbar_img);
        ImageView toolbarBack = findViewById(R.id.toolbar_back);
        TextView username = findViewById(R.id.toolbar_username);
//        red = findViewById(R.id.red);
//        orange = findViewById(R.id.orange);
//        blue = findViewById(R.id.blue);
//        green = findViewById(R.id.green);
//        red.setOnClickListener(this);
//        orange.setOnClickListener(this);
//        blue.setOnClickListener(this);
//        green.setOnClickListener(this);
        linner = findViewById(R.id.question_type);
        linner.setOnClickListener(this);
        toolbarImg.setImageResource(R.mipmap.ic_launcher);
        toolbarBack.setImageResource(R.mipmap.ic_title_back);
        username.setText("User");
        toolbarImg.setOnClickListener(this);
        toolbarBack.setOnClickListener(this);
        TextView toolbarTitle = findViewById(R.id.title_toolbar);
        toolbarTitle.setText(R.string.toolbar_title2);
//        question_typeText = findViewById(R.id.question_typeText);
//        priority_levelText = findViewById(R.id.priority_levelText);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            // 返回按钮
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.question_type:
                if (mpopupWindow != null && mpopupWindow.isShowing())
                {
                    mpopupWindow.dismiss();
                }
                else
                {
                    showPopupWindow();
                }
                break;
//            case R.id.red:
//                question_typeText.setText("缺陷");
//                break;
//            case R.id.orange:
//                question_typeText.setText("改进");
//                break;
//            case R.id.blue:
//                question_typeText.setText("任务");
//                break;
//            case R.id.green:
//                question_typeText.setText("需求");
//                break;
        }

    }

    private void showPopupWindow()
    {
        View contentView = LayoutInflater.from(CreateQuestionActivity.this).inflate(R.layout.question_type, null);
        mpopupWindow = new PopupWindow(contentView,400,240);
        mpopupWindow.setOutsideTouchable(true);
        mpopupWindow.showAsDropDown(linner,280,0);
        mpopupWindow.setFocusable(true);

    }
}
