package com.example.develop.machinedone.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.tool.CircleRangeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineFragment extends Fragment {

    private View inflate;
    private CircleRangeView circleRangeView;
    private List<String> extras;
    private String[] valueArray;
    private Random random;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        inflate = inflater.inflate(R.layout.activity_mine_fragment, container, false);
        init();//初始化

        computations();//运算
        return inflate;
    }

    private void computations() {
        int i=random.nextInt(valueArray.length);
        extras = new ArrayList<>();
        float pressent = (float) i / valueArray.length * 100;

        extras.add("执行能力评分："+pressent+"%");


        circleRangeView.setValueWithAnim(valueArray[i],extras);
    }

    private void init()
    {
        circleRangeView = inflate.findViewById(R.id.circleRangeView);
        ImageView toolbarImg =inflate.findViewById(R.id.toolbar_img);
        ImageView toolbarImg1 = inflate.findViewById(R.id.toolbar_back);
        toolbarImg1.setImageResource(R.mipmap.ic_thok1);
        TextView username = inflate.findViewById(R.id.toolbar_username);
        TextView title = inflate.findViewById(R.id.title_toolbar);
        username.setText("话题");
        title.setText("我的");
        random = new Random();
        valueArray = getResources().getStringArray(R.array.circlerangeview_values);

    }

}
