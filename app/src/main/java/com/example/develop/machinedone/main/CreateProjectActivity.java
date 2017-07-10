package com.example.develop.machinedone.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.develop.machinedone.R;

public class CreateProjectActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_project);
        ImageView toolbarImg = findViewById(R.id.toolbar_img);
        ImageView toolbarImg1 = findViewById(R.id.toolbar_img1);
        TextView username = findViewById(R.id.toolbar_username);
        toolbarImg.setImageResource(R.mipmap.ic_launcher);
        toolbarImg1.setImageResource(R.mipmap.ic_launcher);
        username.setText("User");
       // setSupportActionBar(toolbar);
        toolbarImg.setOnClickListener(this);
        toolbarImg1.setOnClickListener(this);
        TextView toolbarTitle = findViewById(R.id.title_toolbar);
        toolbarTitle.setText(R.string.toolbar_title1);
    }
    public void onClick(View view)
    {
        switch (view.getId())
        {

            case R.id.toolbar_img1:
                //登入头像点击事件
                //Intent intent=new Intent();
                //startActivity(intent);
                Toast.makeText(this, "返回按钮", Toast.LENGTH_SHORT).show();

                break;
            case R.id.toolbar_img:
                //登入头像点击事件
                //Intent intent=new Intent();
                //startActivity(intent);
                Toast.makeText(this, "头像点击事件", Toast.LENGTH_SHORT).show();

                break;
        }



    }
}
