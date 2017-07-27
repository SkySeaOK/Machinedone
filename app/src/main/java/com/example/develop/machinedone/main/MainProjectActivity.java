package com.example.develop.machinedone.main;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.api.ApiService;
import com.example.develop.machinedone.bean.MainList;
import com.example.develop.machinedone.bean.ProblemBean;

import java.lang.reflect.Field;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainProjectActivity extends AppCompatActivity implements View.OnClickListener {

    private SearchView sv;
    private   String url="http://192.168.1.110:8080/";
    private int position;
    private TextView projectTitle;
    private TextView projectUser;
    private ApiService apiService;
    private Retrofit build;
    private TextView projectNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_project);
        projectTitle = findViewById(R.id.project_title);
        projectUser = findViewById(R.id.project_user);
        TextView username = findViewById(R.id.toolbar_username);
        ImageView toolbarImg = findViewById(R.id.toolbar_img);
        ImageView toolbarBack = findViewById(R.id.toolbar_back);
        projectNum = findViewById(R.id.project_num);
        Button createQuestion = findViewById(R.id.create_btn);
        toolbarImg.setImageResource(R.mipmap.title_img);
        toolbarBack.setImageResource(R.mipmap.ic_title_back);
        TextView activeProblem = findViewById(R.id.active_problem);
        sv = findViewById(R.id.searchView);
        //通过getIntent获取AllProject传递过来的position下标
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        username.setText("User");
        //通过retrofit获取网络数据，通过传递过来的position去提取对应的数据
        Retrofit build = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService = build.create(ApiService.class);
        Call<MainList> list = apiService.getList();
        list.enqueue(new Callback<MainList>() {
            @Override
            public void onResponse(Call<MainList> call, Response<MainList> response) {

                List<MainList.MenuitemBean> menuitem = response.body().getMenuitem();
                MainList.MenuitemBean menuitemBean = menuitem.get(position);
                projectTitle.setText(menuitemBean.getValue());
                projectUser.setText(menuitemBean.getOnclick()+"  创建");

            }

            @Override
            public void onFailure(Call<MainList> call, Throwable t) {

            }
        });
        Call<ProblemBean> list_t = apiService.getList_t();
        list_t.enqueue(new Callback<ProblemBean>() {
            @Override
            public void onResponse(Call<ProblemBean> call, Response<ProblemBean> response) {
                List<ProblemBean.MenuitemBean> menuitem = response.body().getMenuitem();
                int size = menuitem.size();
                projectNum.setText(size+"");

            }

            @Override
            public void onFailure(Call<ProblemBean> call, Throwable t) {

            }
        });
        // setSupportActionBar(toolbar);
        toolbarImg.setOnClickListener(this);
        toolbarBack.setOnClickListener(this);
        createQuestion.setOnClickListener(this);
        activeProblem.setOnClickListener(this);
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
    public void onClick(View view)
    {
        switch (view.getId())
        {
            // 返回按钮
            case R.id.toolbar_back:
                finish();
                break;

            case R.id.create_btn:
                Intent intent = new Intent(MainProjectActivity.this,CreateQuestionActivity.class);
                startActivity(intent);
                break;
            case R.id.active_problem:
                Intent intent1 = new Intent(this, ProblemActivity.class);
                startActivity(intent1);
                break;

        }


    }
}
