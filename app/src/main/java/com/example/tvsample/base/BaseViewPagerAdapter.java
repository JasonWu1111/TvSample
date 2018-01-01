package com.example.tvsample.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonWu on 2017/12/27
 */

public abstract class BaseViewPagerAdapter<T> extends PagerAdapter implements ViewPager.OnPageChangeListener{

    private List<T> data = new ArrayList<>();

    private Context mContext;
    private AutoViewPager mView;

    private OnAutoViewPagerItemClickListener listener;

    public BaseViewPagerAdapter(Context context, List<T> data) {
        this.mContext = context;
        this.data = data;
    }

    public BaseViewPagerAdapter(Context context, List<T> data,OnAutoViewPagerItemClickListener listener) {
        this.mContext = context;
        this.data = data;
        this.listener = listener;
    }

    public void init(AutoViewPager viewPager,BaseViewPagerAdapter adapter){
        mView = viewPager;
        mView.setAdapter(this);
        mView.addOnPageChangeListener(this);

        if (data == null || data.size() == 0){
            return;
        }
        //设置初始为中间，这样一开始就能够往左滑动了
        int position = Integer.MAX_VALUE/2 - (Integer.MAX_VALUE/2) % getRealCount();
        mView.setCurrentItem(position);

        mView.start();
        mView.updatePointView(getRealCount());
    }

    public void setListener(OnAutoViewPagerItemClickListener listener) {
        this.listener = listener;
    }

    public void add(T t){
        data.add(t);
        notifyDataSetChanged();
        mView.updatePointView(getRealCount());
    }

    @Override
    public int getCount() {
        return data == null ? 0 : Integer.MAX_VALUE;
    }

    public int getRealCount(){
        return data == null ? 0 : data.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
//        ImageView view = (ImageView) LayoutInflater.from(mContext)
//                .inflate(R.layout.imageview,container,false);
        ImageView view = new ImageView(mContext);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(position % getRealCount(),data.get(position % getRealCount()));
                }
            }
        });

        loadImage(view,position, data.get(position % getRealCount()));
        container.addView(view);

        return view;
    }

    public abstract void loadImage(ImageView view,int position,T t);

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mView.onPageSelected(position % getRealCount());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface OnAutoViewPagerItemClickListener<T> {
        void onItemClick(int position,T t);
    }
}
