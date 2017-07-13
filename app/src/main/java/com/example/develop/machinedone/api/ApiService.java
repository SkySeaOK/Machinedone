package com.example.develop.machinedone.api;

import com.example.develop.machinedone.bean.MainList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by develop on 2017/7/13.
 */

public interface ApiService
{
    //测试listview接口
    @GET("json.txt")
    Call<MainList>getList();

}
