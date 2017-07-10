package com.example.develop.machinedone.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.develop.machinedone.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow()
//                .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                        WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.main_activity);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        ImageView toolbarImg = findViewById(R.id.toolbar_img);
       TextView username = findViewById(R.id.toolbar_username);
        ListView list = findViewById(R.id.main_list);
        Button btn = findViewById(R.id.addlist);
        toolbarImg.setImageResource(R.mipmap.ic_launcher);
        username.setText("User");
        toolbar.setTitle("");
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        btn.setOnClickListener(this);
        toolbarImg.setOnClickListener(this);
    }
    @Override
    //添加数据到listview中
    public void onClick(View view)
    {
       switch (view.getId())
       {

           case R.id.addlist:
               //点击事件 ，打开一个添加项目页面
               //Intent intent=new Intent();
               //startActivity(intent);
               Toast.makeText(this, "is btn", Toast.LENGTH_SHORT).show();

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
