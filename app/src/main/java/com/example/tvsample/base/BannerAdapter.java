package com.example.tvsample.base;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.example.tvsample.adapter.OnItemClickListener;

import java.util.List;

/**
 * Created by JasonWu on 2018/1/1
 */

public class BannerAdapter extends PagerAdapter {
    private List<ImageView> mList;
    private int pos;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        mOnItemClickListener = OnItemClickListener;
    }

    public BannerAdapter(List<ImageView> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
        return arg0 == arg1;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //对ViewPager页号求模取出View列表中要显示的项
        position %= mList.size();
        if (position < 0) {
            position = mList.size() + position;
        }
        ImageView imageView = mList.get(position);
        pos = position;
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent viewParent = imageView.getParent();
        if (viewParent != null) {
            ViewGroup parent = (ViewGroup) viewParent;
            parent.removeView(imageView);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(pos, null);
                }
            }
        });
        container.addView(imageView);
        return imageView;
    }

    //不重写destroyItem会报出java.lang.UnsupportedOperationException
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    }
}
