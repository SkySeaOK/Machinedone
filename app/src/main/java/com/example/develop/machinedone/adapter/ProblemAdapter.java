package com.example.develop.machinedone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.bean.ProblemBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by develop on 2017/7/17.
 */

public class ProblemAdapter extends BaseAdapter
{
   private List<ProblemBean.MenuitemBean> list=new ArrayList<>();
    private  Context view;
    public  ProblemAdapter( Context context ,List<ProblemBean.MenuitemBean> list)
    {
        this.list=list;
        view=context;


    }

    @Override
    public int getCount() {
        return list.size();
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
            inflate = LayoutInflater.from(view).inflate(R.layout.problem_item, null);

        }
        else
        {
            inflate=  convertView;
        }
        TextView num = inflate.findViewById(R.id.position_num);
        TextView titel = inflate.findViewById(R.id.pro_title);
        TextView detail = inflate.findViewById(R.id.pro_detail);
        TextView time = inflate.findViewById(R.id.cre_time);
        Date date = new Date(System.currentTimeMillis());
        num.setText("#"+(i+1));
        titel.setText(list.get(i).getTitle());
        detail.setText(list.get(i).getDetail());
        //time.setText("USER创建于"+date);
        time.setText(list.get(i).getUser()+" 创建于"+date);
        return inflate;

    }
}
