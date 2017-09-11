package com.example.develop.machinedone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class AllProjectActivity extends Fragment implements View.OnClickListener
{
    List<MainList.MenuitemBean> menuitemBeans=new ArrayList<>();
    private MainListAdapter mainListAdapter;
    private ListView listView;
    private View inflate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        inflate = inflater.inflate(R.layout.all_project, container, false);
        return inflate;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();//初始化
    }

    private void initView() {

        ImageView toolbarImg =inflate.findViewById(R.id.toolbar_img);
        ImageView toolbarImg1 = inflate.findViewById(R.id.toolbar_back);
        TextView username = inflate.findViewById(R.id.toolbar_username);
        listView = inflate.findViewById(R.id.main_list);
        mainListAdapter = new MainListAdapter(getActivity(), menuitemBeans);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MainProjectActivity.class);
                intent.putExtra("position", i);
                startActivity(intent);
            }
        });
        at.markushi.ui.CircleButton btn = (at.markushi.ui.CircleButton)inflate.findViewById(R.id.addlist);
        toolbarImg.setImageResource(R.mipmap.title_img);
        toolbarImg1.setImageResource(R.mipmap.ic_thok1);
        username.setText("User");
        btn.setOnClickListener(this);
        toolbarImg.setOnClickListener(this);
        TextView toolbarTitle = inflate.findViewById(R.id.title_toolbar);
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
               Intent intent = new Intent(getActivity(),CreateProjectActivity.class);
               startActivity(intent);

           break;
           case R.id.toolbar_img:
               //登入头像点击事件
               //Intent intent=new Intent();
               //startActivity(intent);
               Toast.makeText(getActivity(), "头像点击事件", Toast.LENGTH_SHORT).show();

           break;
       }





    }
}
