package com.example.develop.machinedone.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.develop.machinedone.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = findViewById(R.id.main_list);
        Button btn = findViewById(R.id.addlist);
        btn.setOnClickListener(this);
    }

    @Override
    //添加数据到listview中
    public void onClick(View view)
    {
        Toast.makeText(this, "is ok", Toast.LENGTH_SHORT).show();

    }
}
