package com.example.develop.machinedone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.bean.LogListViewItem;
import com.example.develop.machinedone.bean.MainList;
import com.example.develop.machinedone.bean.ProblemBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by develop on 2017/8/1.
 */

public class LogListViewAdapter extends BaseAdapter {
    private List<LogListViewItem.MenuitemBean> mainlist;
    private Context view;

    public LogListViewAdapter(Context context,List<LogListViewItem.MenuitemBean> mainlist)//预留传参
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
            inflate = LayoutInflater.from(view).inflate(R.layout.listview_logcat, null);

        }
        else
        {
            inflate=  convertView;
        }
        TextView user = inflate.findViewById(R.id.user_create);
        ImageView logo = inflate.findViewById(R.id.item_img);
        TextView time = inflate.findViewById(R.id.create_usetime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String nowtime = simpleDateFormat.format(date);

        user.setText(mainlist.get(i).getUser());
        logo.setImageResource(R.mipmap.title_img);
        time.setText(nowtime);
        return inflate;
    }
}
