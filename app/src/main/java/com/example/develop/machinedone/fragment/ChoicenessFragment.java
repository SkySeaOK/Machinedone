package com.example.develop.machinedone.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.adapter.TopicListViewAdapter;
import com.example.develop.machinedone.api.ApiService;
import com.example.develop.machinedone.bean.TopicListViewItem;
import com.example.develop.machinedone.model.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by develop on 2017/9/19.
 */

public class ChoicenessFragment extends Fragment {
    private boolean isViewPrepared=false;//是否初始化完成

    private ListView choicenessListview;
    private TopicListViewAdapter topicListViewAdapter;
    private List<TopicListViewItem.MenuitemBean> menuitemBeans = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view  = inflater.inflate(R.layout.choiceness_fragment, container, false);
        initView(view);//初始化
        if (!isViewPrepared&&getUserVisibleHint()) {//尚未初始化view,不能执行initData()方法[会报空指针]

                initData();
                topicListViewAdapter.notifyDataSetChanged();

        }
        isViewPrepared=true;//isViewPrepared判断和赋值位置不能变,考虑setUserVisibleHint更新数据
        return view;
    }
    private void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url.url).addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        final Call<TopicListViewItem> list = apiService.getTopic_json();
        list.enqueue(new Callback<TopicListViewItem>() {
            @Override
            public void onResponse(Call<TopicListViewItem> call, Response<TopicListViewItem> response) {
                menuitemBeans.addAll(response.body().getMenuitem());
                choicenessListview.setAdapter(topicListViewAdapter);
            }

            @Override
            public void onFailure(Call<TopicListViewItem> call, Throwable t) {
                Toast.makeText(getContext(), "aaaaaa", Toast.LENGTH_LONG).show();
            }

        });
    }
    private void initView(View view) {
        choicenessListview=view.findViewById(android.R.id.list);
        topicListViewAdapter = new TopicListViewAdapter(getContext(), menuitemBeans);//添加数据到listview中
    }
    public void setUserVisibleHint(boolean isVisibleToUser) {
        // 判断该Fragment时候已经正在前台显示，就可以知道什么时候去加载数据了
        if (isVisibleToUser && isViewPrepared)
        {
            menuitemBeans.clear();
                initData();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
}
