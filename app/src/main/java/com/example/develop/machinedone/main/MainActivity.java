package com.example.develop.machinedone.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.develop.machinedone.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow()
//                .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                        WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        ListView list = findViewById(R.id.main_list);
        Button btn = findViewById(R.id.addlist);
        toolbar.setTitle("");
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        btn.setOnClickListener(this);
    }
    @Override
    //添加数据到listview中
    public void onClick(View view)
    {
        //点击事件 ，打开一个添加项目页面
        //Intent intent=new Intent();
        //startActivity(intent);
        Toast.makeText(this, "is ok", Toast.LENGTH_SHORT).show();

    }
}
