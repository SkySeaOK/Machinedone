package com.example.develop.machinedone.main;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;

import com.example.develop.machinedone.R;

import java.lang.reflect.Field;

public class MainProjectActivity extends AppCompatActivity implements View.OnClickListener {

    private SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_project);
        ImageView toolbarImg = findViewById(R.id.toolbar_img);
        ImageView toolbarBack = findViewById(R.id.toolbar_back);
        TextView username = findViewById(R.id.toolbar_username);
        toolbarImg.setImageResource(R.mipmap.ic_launcher);
        toolbarBack.setImageResource(R.mipmap.ic_title_back);
        sv = findViewById(R.id.searchView);
        username.setText("User");
        // setSupportActionBar(toolbar);
        toolbarImg.setOnClickListener(this);
        toolbarBack.setOnClickListener(this);
        TextView toolbarTitle = findViewById(R.id.title_toolbar);
        toolbarTitle.setText(R.string.toolbar_title1);
        deletedown();
    }

    private void deletedown() {//去掉搜索框的下划线

        if (sv != null) {
            try {        //--拿到字节码
                Class<?> argClass = sv.getClass();
                //--指定某个私有属性,mSearchPlate是搜索框父布局的名字
                Field ownField = argClass.getDeclaredField("mSearchPlate");
                //--暴力反射,只有暴力反射才能拿到私有属性
                ownField.setAccessible(true);
                View mView = (View) ownField.get(sv);
                //--设置背景
                mView.setBackgroundColor(Color.TRANSPARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {

    }
}
