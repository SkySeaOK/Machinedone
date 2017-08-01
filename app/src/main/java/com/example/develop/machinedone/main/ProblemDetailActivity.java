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
import com.example.develop.machinedone.adapter.LogListViewAdapter;
import com.example.develop.machinedone.api.ApiService;
import com.example.develop.machinedone.bean.LogListViewItem;
import com.example.develop.machinedone.bean.ProblemBean;
import com.example.develop.machinedone.model.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProblemDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView logbtn;
    private ListView log_listview;
    private List<LogListViewItem.MenuitemBean> menuitemBeans = new ArrayList<>();
    private LogListViewAdapter logListViewAdapter;

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
        logbtn = findViewById(R.id.log_btn);
        logbtn.setOnClickListener(this);
        log_listview = findViewById(R.id.log_listview);
        toolbarTitle.setText(R.string.question_detail);
        logListViewAdapter = new LogListViewAdapter(this, menuitemBeans);
//添加数据到listview中
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url.url).addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        final Call<LogListViewItem> list = apiService.getList_three();
        list.enqueue(new Callback<LogListViewItem>() {
            @Override
            public void onResponse(Call<LogListViewItem> call, Response<LogListViewItem> response) {
                menuitemBeans.addAll(response.body().getMenuitem());
                log_listview.setAdapter(logListViewAdapter);
            }

            @Override
            public void onFailure(Call<LogListViewItem> call, Throwable t) {

            }
        });

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
                if (log_listview.getVisibility() == view.VISIBLE) {
                    log_listview.setVisibility(view.GONE);
                } else {
                    log_listview.setVisibility(view.VISIBLE);

                }
                break;
        }
    }

}
