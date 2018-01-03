package com.example.tvsample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tvsample.entity.VideoListInfo;

import java.util.List;

/**
 * Created by JasonWu on 2018/1/1
 */

public class BannerAdapter extends PagerAdapter {
    private List<VideoListInfo.PlayListEntity> mList;
    private int pos;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        mOnItemClickListener = OnItemClickListener;
    }

    public BannerAdapter(List<VideoListInfo.PlayListEntity> list, Context context) {
        mContext = context;
        mList = list;
        notifyDataSetChanged();
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
        ImageView imageView = new ImageView(mContext);
        Glide.with(mContext)
                .load(mList.get(position).getImageUrl())
                .into(imageView);
        pos = position;
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent viewParent = imageView.getParent();
        if (viewParent != null) {
            ViewGroup parent = (ViewGroup) viewParent;
            parent.removeView(imageView);
        }
        imageView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(pos, null, mList.get(pos).getPlayListId());
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
