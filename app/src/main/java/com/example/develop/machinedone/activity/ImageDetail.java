package com.example.develop.machinedone.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.develop.machinedone.R;

import java.util.List;

public class ImageDetail extends AppCompatActivity {
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        ImageView imgView = findViewById(R.id.img_detail);
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        Glide.with(this).load(key).into(imgView);//设置

    }
}
