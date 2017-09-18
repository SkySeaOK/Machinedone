package com.example.develop.machinedone.fragment;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.adapter.CityAdapter;
import com.example.develop.machinedone.tool.City;
import com.example.develop.machinedone.tool.LetterComparator;
import com.example.develop.machinedone.tool.PinnedHeaderDecoration;
import com.example.develop.machinedone.tool.WaveSideBarView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommunityFragment extends Fragment
{
    private View inflate;
    RecyclerView mRecyclerView;
    WaveSideBarView mSideBarView;
    CityAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        inflate = inflater.inflate(R.layout.activity_community_fragment, container, false);
       init();//初始化
        //test();
        return inflate;
    }

    private void init()
    {
        mRecyclerView = inflate.findViewById(R.id.recycler_view);
        mSideBarView = inflate.findViewById(R.id.side_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final PinnedHeaderDecoration decoration = new PinnedHeaderDecoration();
        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator()
        {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition)
            {
                return true;
            }
        });
        mRecyclerView.addItemDecoration(decoration);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
                Type listType = new TypeToken<ArrayList<City>>() {
                }.getType();
                Gson gson = new Gson();
                final List<City> list = gson.fromJson(City.DATA, listType);
                Collections.sort(list, new LetterComparator());
                        adapter = new CityAdapter(getContext(), list);
                        mRecyclerView.setAdapter(adapter);

//            }
//        }).start();
        mSideBarView.setOnTouchLetterChangeListener(new WaveSideBarView.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                int pos = adapter.getLetterPosition(letter);

                if (pos != -1) {
                    mRecyclerView.scrollToPosition(pos);
                    LinearLayoutManager mLayoutManager =
                            (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(pos, 0);
                }
            }
        });

    }

}
