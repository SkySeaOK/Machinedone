package com.example.develop.machinedone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.bean.TopicListViewItem;

import java.util.List;

/**
 * Created by develop on 2017/9/18.
 */

public class TopicListViewAdapter extends BaseAdapter {
    private List<TopicListViewItem.MenuitemBean> mainlist;
    private Context view;

    public TopicListViewAdapter(Context context, List<TopicListViewItem.MenuitemBean> mainlist)//预留传参
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
            inflate = LayoutInflater.from(view).inflate(R.layout.topic_item, null);

        }
        else
        {
            inflate=  convertView;
        }
        TextView topic = inflate.findViewById(R.id.topic_text);
        TextView agreetext = inflate.findViewById(R.id.agreetext);
        TextView thanktext = inflate.findViewById(R.id.thanktext);
        ImageView topicimage = inflate.findViewById(R.id.topicimage);
        topic.setText(mainlist.get(i).getTopic());
        agreetext.setText(mainlist.get(i).getAgreecount());
        thanktext.setText(mainlist.get(i).getThankcount());
//        int a = Integer.parseInt(mainlist.get(i).getImageid());
        topicimage.setImageResource(R.mipmap.title_img);
        return inflate;
    }
}
