package com.example.develop.machinedone.main;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.develop.machinedone.R;

public class ProblemActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title;
    boolean x=false;
    private PopupWindow mpopupWindow;
    private LinearLayout linner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        title = findViewById(R.id.title_toolbar);
        linner = findViewById(R.id.toolbar_linnear);
        TextView username = findViewById(R.id.toolbar_username);
        ImageView toolbarImg = findViewById(R.id.toolbar_img);
        ImageView toolbarBack = findViewById(R.id.toolbar_back);

        toolbarImg.setImageResource(R.mipmap.ic_launcher);
        toolbarBack.setImageResource(R.mipmap.ic_title_back);
        title.setText("活动问题 ↑");
        username.setText("User");
        toolbarBack.setOnClickListener(this);
        title.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.title_toolbar:


                if(x){
                    title.setText("活动问题 ↑");

                    x=false;
                }else{
                    title.setText("活动问题 ↓");
                    showPopupWindow();
                    x=true;
                }
                break;
            case R.id.toolbar_back: finish();
                break;
        }
    }

    private void showPopupWindow()
    {
        View contentView = LayoutInflater.from(ProblemActivity.this).inflate(R.layout.pop_layout, null);
        mpopupWindow = new PopupWindow(contentView);
        mpopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mpopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mpopupWindow.setOutsideTouchable(true);
        mpopupWindow.showAsDropDown(linner);

    }
}
