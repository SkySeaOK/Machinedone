package com.example.develop.machinedone.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.develop.machinedone.bean.MainList;

import java.util.List;

/**
 * Created by develop on 2017/7/7.
 */
//未完成
public class MainListAdapter extends BaseAdapter
{
   private String title;
    private String detail;
    private final MainList mainL;
    private List<MainList> mainlist;

    public MainListAdapter(List<MainList> mainlist)//预留传参
    {

        this.mainlist=mainlist;

        mainL = mainlist.get(0);




    }
    @Override
    public int getCount() {
        return mainlist.size() ;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
