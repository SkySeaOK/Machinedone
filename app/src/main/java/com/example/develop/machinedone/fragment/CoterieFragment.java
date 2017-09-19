package com.example.develop.machinedone.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.develop.machinedone.R;
import com.example.develop.machinedone.adapter.ContactAdapter;
import com.example.develop.machinedone.adapter.ContactScrollerAdapter;
import com.example.develop.machinedone.mock.Contact;
import com.example.develop.machinedone.tool.BubbleScroller;
import com.example.develop.machinedone.tool.ScrollerListener;

import java.util.List;

public class CoterieFragment extends Fragment {

    private View inflate;
    BubbleScroller scroller;
    RecyclerView recycler;
    private ContactScrollerAdapter mContactScrollerAdapter;
    private ContactAdapter mContactAdapter;
    private LinearLayoutManager mLayoutManager;
    private boolean mProgrammaticScroll = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        inflate = inflater.inflate(R.layout.activity_coterie_fragment, container, false);

        scroller =  inflate.findViewById(R.id.bubble_scroller);
        recycler = inflate.findViewById(R.id.recycler);
        List<Contact> contactList = Contact.mocks(getContext());
        mContactScrollerAdapter = new ContactScrollerAdapter(contactList);
        mContactAdapter = new ContactAdapter(getContext(), contactList, mContactScrollerAdapter);
        mLayoutManager = new LinearLayoutManager(getContext());
        scroller.setScrollerListener(mScrollerListener);
       scroller.setSectionScrollAdapter(mContactScrollerAdapter);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(mContactAdapter);
        scroller.showSectionHighlight(0);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (mProgrammaticScroll) {
                    mProgrammaticScroll = false;
                    return;
                }
                final int firstVisibleItemPosition = mLayoutManager.findFirstCompletelyVisibleItemPosition();
                scroller.showSectionHighlight(
                        mContactScrollerAdapter.sectionFromPosition(firstVisibleItemPosition));
            }
        });






        return inflate;

    }
    private final ScrollerListener mScrollerListener = new ScrollerListener() {
        @Override
        public void onSectionClicked(int sectionPosition) {
            recycler.smoothScrollToPosition(
                    mContactScrollerAdapter.positionFromSection(sectionPosition));
            mProgrammaticScroll = true;
        }

        @Override
        public void onScrollPositionChanged(float percentage, int sectionPosition) {
            recycler.smoothScrollToPosition(
                    mContactScrollerAdapter.positionFromSection(sectionPosition));
            mProgrammaticScroll = true;
        }
    };
}
