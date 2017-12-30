package com.vj.myapplication.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 *
 * Created by VJ on 12/31/2017.
 */

public abstract class ViewHolder extends RecyclerView.ViewHolder  {
    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
