package com.example.tvsample.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tvsample.R;
import com.example.tvsample.base.BaseRvAdapter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonWu on 2018/1/3
 */

public class SimpleTabAdapter extends BaseRvAdapter<String> {

    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, Boolean> map = new HashMap<>();

    //当前播放的位置
    private int curIndex = 0;
    //记录切换位置前的位置
    private int preIndex = -1;

    public SimpleTabAdapter(Context context, int size) {
        super(context);
        for (int i = 1; i < size; i++) {
            map.put(i, false);
        }
        map.put(0, true);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleTabViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_simple_tab, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SimpleTabViewHolder)holder).bind(position);
    }

    class SimpleTabViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tab_text)
        TextView tabText;

        SimpleTabViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position){
            tabText.setText(getData().get(position));
            tabText.setSelected(map.get(position));

            itemView.setOnClickListener(v -> {
                if (position != curIndex) {
                    preIndex = curIndex;
                    curIndex = position;
                    map.put(preIndex, false);
                    map.put(curIndex, true);
                    notifyDataSetChanged();
//                    onItemClickListener.onClick(position, null, null);
                }
            });
        }

    }
}
