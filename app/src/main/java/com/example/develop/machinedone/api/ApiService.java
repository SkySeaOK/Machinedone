package com.example.develop.machinedone.api;

import com.example.develop.machinedone.bean.MainList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by develop on 2017/7/11.
 */

public interface ApiService
{
    //测试接口
    @GET("json.txt")
    Call<MainList>getList();
}
