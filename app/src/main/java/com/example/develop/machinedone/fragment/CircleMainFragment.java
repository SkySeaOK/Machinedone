package com.example.develop.machinedone.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.tool.TabsView;

import java.util.ArrayList;
import java.util.List;

public class CircleMainFragment extends Fragment implements View.OnClickListener {

    private ViewPager mViewPager;
    private TabsView mTabs;

    private List<Fragment> fragments;
    private MyAdapter adapter;

    private View inflate;
    private PopupWindow operate_popupWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.activity_circle_main_fragment, container, false);
        return inflate;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();//初始化
        ImageView toolbarImg =inflate.findViewById(R.id.search_img);
        ImageView toolbarImg1 = inflate.findViewById(R.id.fragment_back);
        ImageView addImg = inflate.findViewById(R.id.add_img);
        TextView title = inflate.findViewById(R.id.fragment_title);
        toolbarImg.setImageResource(R.mipmap.ic_searchicon);
        toolbarImg1.setImageResource(R.mipmap.ic_thok1);
        addImg.setImageResource(R.mipmap.ic_add);
        addImg.setOnClickListener(this);
        title.setText("圈子");
    }

    private void initData() {

        mViewPager = (ViewPager) inflate.findViewById(R.id.circleviewpager);
        mTabs = (TabsView) inflate.findViewById(R.id.circletabslayout);

        fragments = new ArrayList<Fragment>();
        fragments.add(new CoterieFragment());
        fragments.add(new MZModeBannerFragment());
        fragments.add(new ChoicenessFragment());
        fragments.add(new ChoicenessFragment());
        //设置适配器
        adapter = new MyAdapter(getFragmentManager());
        mViewPager.setAdapter(adapter);

//        mViewPager.setOffscreenPageLimit(4);
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_img:
                showOperatePopupWindow();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showOperatePopupWindow() {
        View contentView =  getActivity().getLayoutInflater().inflate(R.layout.add_topic_item, null);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

//这里就可自定义在上方和下方了 ，这种方式是为了确定在某个位置，某个控件的左边，右边，上边，下边都可以
        operate_popupWindow = new PopupWindow(contentView, 0, 0);
        operate_popupWindow.setBackgroundDrawable(new BitmapDrawable());//注意这里如果不设置，下面的setOutsideTouchable(true);允许点击外部消失会失效
        operate_popupWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
        operate_popupWindow.setFocusable(true);
// 获得目标控件位置
        operate_popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        operate_popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        operate_popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        WindowManager windowManager = getActivity().getWindowManager();
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        //当弹出Popupwindow时，背景变半透明
        params.alpha = 0.7f;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(params);
        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复1f
        operate_popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
                params.alpha = 1f;
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getActivity().getWindow().setAttributes(params);
            }
        });
        operate_popupWindow.setAnimationStyle(R.style.AnimationFade);
        operate_popupWindow.update();


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

