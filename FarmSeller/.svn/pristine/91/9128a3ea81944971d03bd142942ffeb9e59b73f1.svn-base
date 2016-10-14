package com.aotuo.vegetable.adapter;

import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/21.
 */

public class GuidePagerAdapter extends android.support.v4.view.PagerAdapter {
    private ArrayList<View> datalist;

    public GuidePagerAdapter(ArrayList<View> datalist) {
        this.datalist = datalist;
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        // TODO Auto-generated method stub
        ((ViewPager) arg0).removeView(datalist.get(arg1));
    }

    @Override
    public Object instantiateItem(View arg0, int arg1) {
        // TODO Auto-generated method stub
        ((ViewPager) arg0).addView(datalist.get(arg1));
        return datalist.get(arg1);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
        // TODO Auto-generated method stub
    }

    @Override
    public Parcelable saveState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void startUpdate(View arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void finishUpdate(View arg0) {
        // TODO Auto-generated method stub

    }
}
