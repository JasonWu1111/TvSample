package com.example.tvsample.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.example.tvsample.R;
import com.example.tvsample.adapter.OnItemClickListener;
import com.example.tvsample.adapter.BannerAdapter;
import com.example.tvsample.entity.VideoListInfo;
import com.example.tvsample.module.YouTubePlayerActivity;
import com.example.tvsample.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JasonWu on 2018/1/1
 */

public class BannerView extends RelativeLayout {
    @BindView(R.id.banner_viewpager)
    ViewPager viewPager;
    @BindView(R.id.banner_points_group)
    LinearLayout points;

    private CompositeDisposable compositeDisposable;

    //选中显示Indicator
    private int selectedRes = R.drawable.point_selected;
    //非选中显示Indicator
    private int unSelectRes = R.drawable.point_unselect;

    //当前页的下标
//    private int currentPos;
    private boolean isStopScroll = false;


    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.view_banner, this, true);
        ButterKnife.bind(this);
    }


    //图片轮播需要传入参数
    public void init(List<VideoListInfo.PlayListEntity> dataList) {
        clear();
        if (dataList.size() == 0) {
            this.setVisibility(GONE);
            return;
        }
        List<VideoListInfo.PlayListEntity> bannerList = new ArrayList<>();
        bannerList.addAll(dataList);
        final int pointSize;
        pointSize = bannerList.size();
        if (pointSize == 2) {
            bannerList.addAll(dataList);
        }
        //判断是否清空 指示器点
        if (points.getChildCount() != 0) {
            points.removeAllViewsInLayout();
        }
        //初始化与个数相同的指示器点
        for (int i = 0; i < pointSize; i++) {
            View dot = new View(getContext());
            dot.setBackgroundResource(unSelectRes);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DisplayUtil.dp2px(getContext(), 5),
                    DisplayUtil.dp2px(getContext(), 5));
            params.leftMargin = 10;
            dot.setLayoutParams(params);
            dot.setEnabled(false);
            points.addView(dot);
        }
        points.getChildAt(0).setBackgroundResource(selectedRes);

        //监听图片轮播，改变指示器状态
        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                pos = pos % pointSize;
//                currentPos = pos;
                for (int i = 0; i < points.getChildCount(); i++) {
                    points.getChildAt(i).setBackgroundResource(unSelectRes);
                }
                points.getChildAt(pos).setBackgroundResource(selectedRes);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (isStopScroll) {
                            startScroll();
                        }
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        stopScroll();
                        clear();
                        break;
                }
            }
        });
        BannerAdapter bannerAdapter = new BannerAdapter(dataList, getContext());
        viewPager.setAdapter(bannerAdapter);
        bannerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position, String playListId) {
                YouTubePlayerActivity.launch(getContext(), playListId, 0);
            }
        });

        startScroll();
    }

    //图片开始轮播
    private void startScroll() {
        compositeDisposable = new CompositeDisposable();
        isStopScroll = false;
        //轮播时间
        int delayTime = 3;
        Disposable disposable = Observable.timer(delayTime, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (isStopScroll) {
                            return;
                        }
                        isStopScroll = true;
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                });
        compositeDisposable.add(disposable);
    }


    //图片停止轮播
    private void stopScroll() {
        isStopScroll = true;
    }

    public void clear() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
