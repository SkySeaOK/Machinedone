package com.example.develop.machinedone.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.adapter.MainListAdapter;
import com.example.develop.machinedone.api.ApiService;
import com.example.develop.machinedone.bean.MainList;
import com.example.develop.machinedone.model.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllProjectActivity extends AppCompatActivity implements View.OnClickListener
{
    List<MainList.MenuitemBean> menuitemBeans=new ArrayList<>();
    private MainListAdapter mainListAdapter;
    private ListView listView;
//    private   String url="http://192.168.1.110:8080/";
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
        mainListAdapter = new MainListAdapter(this, menuitemBeans);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AllProjectActivity.this, MainProjectActivity.class);
                intent.putExtra("position",i);
                startActivity(intent);
            }
        });
        Button btn = findViewById(R.id.addlist);
        toolbarImg.setImageResource(R.mipmap.title_img);
        toolbarImg1.setImageResource(R.mipmap.ic_thok1);
        username.setText("User");
        btn.setOnClickListener(this);
        toolbarImg.setOnClickListener(this);
        TextView toolbarTitle = findViewById(R.id.title_toolbar);
        toolbarTitle.setText(R.string.toolbar_title);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url.url).addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        final Call<MainList> list = apiService.getList();
        list.enqueue(new Callback<MainList>() {
            @Override
            public void onResponse(Call<MainList> call, Response<MainList> response) {
                menuitemBeans.addAll(response.body().getMenuitem());
                listView.setAdapter(mainListAdapter);
            }

            @Override
            public void onFailure(Call<MainList> call, Throwable t) {

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
