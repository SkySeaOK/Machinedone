package com.example.develop.machinedone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.develop.machinedone.R;
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
    private List<MainList.MenuitemBean> mainlist;
    private  Context view;

    public MainListAdapter(Context context,List<MainList.MenuitemBean> mainlist)//预留传参
    {

        this.mainlist=mainlist;
        view = context;

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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View inflate;
        if (convertView==null) {
            inflate = LayoutInflater.from(view).inflate(R.layout.main_list_item,null);

        }
        else
        {
            inflate=  convertView;
        }
        TextView textTitle = inflate.findViewById(R.id.list_title);
        TextView textDetail = inflate.findViewById(R.id.list_detail);
        MainList.MenuitemBean mainList = mainlist.get(position);
        textTitle.setText(  mainList.getValue());
        textDetail.setText(mainList.getOnclick());


        return inflate;
    }
}
