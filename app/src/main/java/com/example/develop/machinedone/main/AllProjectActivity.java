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

public class AllProjectActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow()
//                .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                        WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.all_project);
        ImageView toolbarImg = findViewById(R.id.toolbar_img);
        ImageView toolbarImg1 = findViewById(R.id.toolbar_back);
        TextView username = findViewById(R.id.toolbar_username);
        ListView list = findViewById(R.id.main_list);
        Button btn = findViewById(R.id.addlist);
        toolbarImg.setImageResource(R.mipmap.ic_launcher);
        toolbarImg1.setImageResource(R.mipmap.ic_launcher);
        username.setText("User");
        btn.setOnClickListener(this);
        toolbarImg.setOnClickListener(this);
        TextView toolbarTitle = findViewById(R.id.title_toolbar);
        toolbarTitle.setText(R.string.toolbar_title);
    }
    @Override
    //添加数据到listview中
    public void onClick(View view)
    {
       switch (view.getId())
       {

           case R.id.addlist:
               //点击事件 ，打开一个添加项目页面
               Intent intent = new Intent(AllProjectActivity.this,CreateProjectActivity.class);
               startActivity(intent);

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
