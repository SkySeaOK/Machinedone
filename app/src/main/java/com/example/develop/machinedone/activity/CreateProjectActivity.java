package com.example.develop.machinedone.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.develop.machinedone.R;

public class CreateProjectActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_project);
        ImageView toolbarImg = findViewById(R.id.toolbar_img);
        ImageView toolbarBack = findViewById(R.id.toolbar_back);
        TextView username = findViewById(R.id.toolbar_username);
        Button creatBtn = findViewById(R.id.create_btn);
        toolbarImg.setImageResource(R.mipmap.title_img);
        toolbarBack.setImageResource(R.mipmap.ic_title_back);
        username.setText("User");
       // setSupportActionBar(toolbar);
        creatBtn.setOnClickListener(this);
        toolbarImg.setOnClickListener(this);
        toolbarBack.setOnClickListener(this);
        TextView toolbarTitle = findViewById(R.id.title_toolbar);
        toolbarTitle.setText(R.string.toolbar_title1);
    }
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.toolbar_back:
                //关闭当前页面
                finish();
                break;
            case R.id.toolbar_img:

                Toast.makeText(this, "头像点击事件", Toast.LENGTH_SHORT).show();
                break;
            case R.id.create_btn:
                //提交参数到AllProjectActivity
                break;
        }



    }
}
