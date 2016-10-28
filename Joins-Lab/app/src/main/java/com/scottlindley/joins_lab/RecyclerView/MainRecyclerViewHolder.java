package com.scottlindley.joins_lab.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scottlindley.joins_lab.R;

/**
 * Created by Scott Lindley on 10/28/2016.
 */

public class MainRecyclerViewHolder extends RecyclerView.ViewHolder{
    public TextView mText;

    public MainRecyclerViewHolder(View itemView) {
        super(itemView);
        mText = (TextView)itemView.findViewById(R.id.data_text);
    }
}
