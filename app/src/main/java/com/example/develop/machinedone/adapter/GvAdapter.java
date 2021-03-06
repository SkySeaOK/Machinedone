package com.example.develop.machinedone.adapter;

/**
 * Created by develop on 2017/7/25.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.develop.machinedone.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class GvAdapter extends BaseAdapter{
    private Context context;
    private int mMaxPosition;//
    private List<String> list;
    private ViewHolder vh=null;

    public GvAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        mMaxPosition=list.size()+1;
        return mMaxPosition;
    }
    public int getMaxPosition(){
        return mMaxPosition;
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View v, ViewGroup
            parent)
    {

        if (v==null){
            vh=new ViewHolder();
            v= LayoutInflater.from(context).inflate(R.layout.phone_item,parent,false);
            vh.img= v.findViewById(R.id.img);
            vh.demimg=v.findViewById(R.id.delimg);

            v.setTag(vh);
        }else{
            vh= (ViewHolder) v.getTag();
        }
        if (position==mMaxPosition-1){//说明要显示
            Glide.with(context).load(R.mipmap.ic_photo).dontAnimate()
                    .centerCrop().into(vh.img);
                vh.img.setVisibility(View.GONE);
//            vh.img.setImageResource(R.drawable.id_photo);
                vh.img.setVisibility(View.VISIBLE);
                vh.demimg.setVisibility(View.GONE);
                if (position==6&&mMaxPosition==7){//设置最大6个。那么达到最大，就隐藏。
//                vh.img.setImageResource(R.drawable.id_photo);
            }
        }else{//设置图片。
            vh.demimg.setVisibility(View.VISIBLE);
            Glide.with(context).load(list.get(position)).into(vh.img);//设置
        }
        vh.demimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        return v;
    }

    public class ViewHolder{
        public ImageView img,demimg;
    }

}
