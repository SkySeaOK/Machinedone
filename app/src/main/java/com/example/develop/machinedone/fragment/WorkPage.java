package com.example.develop.machinedone.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.fragment.TabsView.OnTabsItemClickListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WorkPage extends Fragment {

    private ViewPager mViewPager;
    private TabsView mTabs;

    private List<Fragment> fragments;
    private MyAdapter adapter;

    private View inflate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        inflate = inflater.inflate(R.layout.activity_work_page, container, false);
        return inflate;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();//初始化
    }

    private void initData() {
        mViewPager = (ViewPager) inflate.findViewById(R.id.viewpager);
        mTabs = (TabsView) inflate.findViewById(R.id.tabslayout);
        String[] titles = {"消息", "圈子", "工作", "我的"};

        fragments = new ArrayList<Fragment>();
        fragments.add(TextFragment.newInstance(titles[0]));
        fragments.add(TextFragment.newInstance(titles[1]));
        fragments.add(TextFragment.newInstance(titles[2]));
        fragments.add(TextFragment.newInstance(titles[3]));
        //设置适配器
        adapter = new MyAdapter(getFragmentManager());
        mViewPager.setAdapter(adapter);
        //初始化选项卡
        mTabs.setTabs("选项卡1","选项卡2","选项卡3","选项卡4");
        mTabs.setOnTabsItemClickListener(new OnTabsItemClickListener() {

            @Override
            public void onClick(View view, int position) {
                mViewPager.setCurrentItem(position, true);
            }
        });
        //
        mViewPager.addOnPageChangeListener(new OnPageChangeListener() {

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
