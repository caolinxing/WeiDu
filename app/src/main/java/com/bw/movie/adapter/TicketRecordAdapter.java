package com.bw.movie.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

public class TicketRecordAdapter extends FragmentPagerAdapter {

    private List<Fragment>fragments;
    private String [] recordTitles = {"待付款","已支付"};

    public TicketRecordAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragments = fragmentList;
    }



    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return recordTitles[position];
    }
}
