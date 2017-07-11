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
import com.example.develop.machinedone.adapter.MainListAdapter;
import com.example.develop.machinedone.api.ApiService;
import com.example.develop.machinedone.bean.MainList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllProjectActivity extends AppCompatActivity implements View.OnClickListener {

   private String url="http://192.168.14.2:8080/";
  private List<MainList.MenuitemBean>list= new ArrayList<>();
    private ListView listView;
    private MainListAdapter mainListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        getWindow()
//                .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                        WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.all_project);
        ImageView toolbarImg = findViewById(R.id.toolbar_img);
        ImageView toolbarImg1 = findViewById(R.id.toolbar_back);
        TextView username = findViewById(R.id.toolbar_username);
        listView = findViewById(R.id.main_list);
        //将页面和list数据传入 适配器 （MainListAdapter）
        mainListAdapter = new MainListAdapter(this, list);
        Button btn = findViewById(R.id.addlist);
        toolbarImg.setImageResource(R.mipmap.ic_launcher);
        toolbarImg1.setImageResource(R.mipmap.ic_launcher);
        username.setText("User");
        btn.setOnClickListener(this);
        toolbarImg.setOnClickListener(this);
        TextView toolbarTitle = findViewById(R.id.title_toolbar);
        toolbarTitle.setText(R.string.toolbar_title);

        //使用Retrofit 获取网络数据
        Retrofit build = new Retrofit.Builder().baseUrl(url)
                // 解析json
                .addConverterFactory(GsonConverterFactory.create()).build();
        //关联到ApiService
        ApiService apiService = build.create(ApiService.class);
        //获取服务器回调数据
        Call<MainList> mainListCall = apiService.getList();
        mainListCall.enqueue(new Callback<MainList>() {
            @Override
            public void onResponse(Call<MainList> call, Response<MainList> response)
            {
                //使用response获得参数， 添加到 list容器中， 用于listview传入适配器
             list.addAll(response.body().getMenuitem());
                listView.setAdapter(mainListAdapter);
            }

            @Override
            public void onFailure(Call<MainList> call, Throwable t)
            {
                //网络连接失败时调用此方法
            }
        });

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