package com.example.develop.machinedone.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.adapter.ProblemAdapter;
import com.example.develop.machinedone.api.ApiService;
import com.example.develop.machinedone.bean.ProblemBean;
import com.example.develop.machinedone.model.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProblemActivity extends AppCompatActivity implements View.OnClickListener {
    List<ProblemBean.MenuitemBean> menuitemBeans=new ArrayList<>();
    private TextView title;
    boolean x=false;
    private PopupWindow mpopupWindow;
    private LinearLayout linner;
    private ListView listView;
//    private   String url="http://192.168.1.110:8080/";
    private ProblemAdapter problemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        title = findViewById(R.id.title_toolbar);
        linner = findViewById(R.id.toolbar_linnear);
        TextView username = findViewById(R.id.toolbar_username);
        ImageView toolbarImg = findViewById(R.id.toolbar_img);
        ImageView toolbarBack = findViewById(R.id.toolbar_back);
        Button addPro = findViewById(R.id.add_pro);
        listView = findViewById(R.id.pro_listView);
        problemAdapter = new ProblemAdapter(this, menuitemBeans);
        toolbarImg.setImageResource(R.mipmap.title_img);
        toolbarBack.setImageResource(R.mipmap.ic_title_back);
        title.setText("活动问题 ↑");
        username.setText("User");
        addPro.setOnClickListener(this);
        toolbarBack.setOnClickListener(this);
        title.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ProblemActivity.this,ProblemDetailActivity.class);
                startActivity(intent);
            }
        });
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url.url).addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        final Call<ProblemBean> list = apiService.getList_t();
        list.enqueue(new Callback<ProblemBean>() {
            @Override
            public void onResponse(Call<ProblemBean> call, Response<ProblemBean> response) {
                menuitemBeans.addAll(response.body().getMenuitem());
                listView.setAdapter(problemAdapter);
            }

            @Override
            public void onFailure(Call<ProblemBean> call, Throwable t) {

            }
        });
    }


    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.title_toolbar:


                if(x){
                    title.setText("活动问题 ↑");
                    mpopupWindow.dismiss();
                    x=false;
                }else{
                    title.setText("活动问题 ↓");
                    showPopupWindow();
                    x=true;
                }
                break;
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.add_pro:
             startActivity( new Intent(this,CreateQuestionActivity.class));
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
