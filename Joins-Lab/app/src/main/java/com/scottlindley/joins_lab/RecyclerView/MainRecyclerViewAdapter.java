package com.scottlindley.joins_lab.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scottlindley.joins_lab.R;

import java.util.List;

/**
 * Created by Scott Lindley on 10/28/2016.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewHolder>{
    private List<String> mList;

    public MainRecyclerViewAdapter(List<String> list) {
        mList = list;
    }

    @Override
    public MainRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list,null);
        return new MainRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainRecyclerViewHolder holder, int position) {
        holder.mText.setText(mList.get(position));
        Log.d("TAG", "onBindViewHolder: "+holder.mText.getText().toString());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void replaceData(List<String> list){
        mList = list;
        notifyDataSetChanged();
    }
}
