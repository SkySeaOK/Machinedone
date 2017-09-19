package com.example.develop.machinedone.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.tool.TabsView;

import java.util.ArrayList;
import java.util.List;

public class CircleMainFragment extends Fragment {

    private ViewPager mViewPager;
    private TabsView mTabs;

    private List<Fragment> fragments;
    private MyAdapter adapter;

    private View inflate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.activity_circle_main_fragment, container, false);
        initData();//初始化
        return inflate;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageView toolbarImg = inflate.findViewById(R.id.toolbar_img);
        ImageView toolbarImg1 = inflate.findViewById(R.id.toolbar_back);
        TextView username = inflate.findViewById(R.id.toolbar_username);
        TextView title = inflate.findViewById(R.id.title_toolbar);
        toolbarImg.setImageResource(R.mipmap.title_img);
        toolbarImg1.setImageResource(R.mipmap.ic_thok1);
        username.setText("User");
        title.setText("工作");
    }

    private void initData() {

        mViewPager = (ViewPager) inflate.findViewById(R.id.circleviewpager);
        mTabs = (TabsView) inflate.findViewById(R.id.circletabslayout);

        fragments = new ArrayList<Fragment>();
        fragments.add(new CommunityFragment());
        fragments.add(new MZModeBannerFragment());
        fragments.add(new ChoicenessFragment());
        fragments.add(new ChoicenessFragment());
        //设置适配器
        adapter = new MyAdapter(getFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(4);
        //初始化选项卡
        mTabs.setTabs("通讯录", "话题", "精选", "收藏");
        mTabs.setOnTabsItemClickListener(new TabsView.OnTabsItemClickListener() {

            @Override
            public void onClick(View view, int position) {
                mViewPager.setCurrentItem(position, true);
            }
        });
        //
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mTabs.setCurrentTab(position, true);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}

