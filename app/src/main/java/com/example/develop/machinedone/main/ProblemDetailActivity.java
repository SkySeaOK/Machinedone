package com.example.develop.machinedone.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.develop.machinedone.R;

public class ProblemDetailActivity extends AppCompatActivity implements View.OnClickListener {

    TextView logbtn;
    ListView log_listview;
    private PopupWindow log_popupWindow;
    private boolean x = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_detail);
        ImageView toolbarImg = findViewById(R.id.toolbar_img);
        ImageView toolbarImg1 = findViewById(R.id.toolbar_back);
        TextView username = findViewById(R.id.toolbar_username);
        toolbarImg.setImageResource(R.mipmap.title_img);
        toolbarImg1.setImageResource(R.mipmap.ic_title_back);
        username.setText("User");
        toolbarImg.setOnClickListener(this);
        toolbarImg1.setOnClickListener(this);
        TextView toolbarTitle = findViewById(R.id.title_toolbar);
        View contentView = LayoutInflater.from(ProblemDetailActivity.this).inflate(R.layout.log_listview, null);
        logbtn = findViewById(R.id.log_btn);
        logbtn.setOnClickListener(this);
        log_listview = findViewById(R.id.log_listview);
        toolbarTitle.setText(R.string.question_detail);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                //关闭当前页面
                finish();
                break;
            case R.id.toolbar_img:
                Toast.makeText(this, "头像点击事件", Toast.LENGTH_SHORT).show();
                break;
            case R.id.log_btn:
                if (x) {
                    log_popupWindow.dismiss();
                    x = false;
                } else {
                    showPopupWindow();
                    x = true;
                }
                break;
        }
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(ProblemDetailActivity.this).inflate(R.layout.log_listview, null);
        log_popupWindow = new PopupWindow(contentView, 400, 0);
        log_popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        log_popupWindow.setOutsideTouchable(true);
        log_popupWindow.showAsDropDown(logbtn, 0, 0);
        log_popupWindow.setFocusable(true);

    }
}
