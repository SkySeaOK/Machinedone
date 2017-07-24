package com.example.develop.machinedone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.bean.MainList;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private Context view;

    public MainListAdapter(Context context,List<MainList.MenuitemBean> mainlist)//预留传参
    {
        this.mainlist=mainlist;
        view=context;
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
    public View getView(int i, View convertView, ViewGroup viewGroup)
    {
        View inflate;
        if (convertView==null) {
            inflate = LayoutInflater.from(view).inflate(R.layout.allproject_item, null);

        }
        else
        {
            inflate=  convertView;
        }
        TextView titel = inflate.findViewById(R.id.item_title);
        ImageView logo = inflate.findViewById(R.id.item_logo);
        TextView time = inflate.findViewById(R.id.create_time);
        long l = System.currentTimeMillis();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String nowtime = simpleDateFormat.format(date);

        titel.setText(mainlist.get(i).getValue());
        logo.setImageResource(R.mipmap.ic_itemlogo);
        time.setText(mainlist.get(i).getOnclick()+"创建于"+date);
        return inflate;
    }
}
