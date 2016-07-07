package com.example.user.menutestapp.RecyclerViewMenuAll.ViewHolders;

import com.example.user.menutestapp.R;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 04.07.2016.
 */
public class NestedViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNestedData;
    private ImageButton nestedArrow;

    public NestedViewHolder(final View viewItem) {
        super(viewItem);
        tvNestedData = (TextView) viewItem.findViewById(R.id.nested_data);
        nestedArrow = (ImageButton) viewItem.findViewById(R.id.nested_arrow);
    }
}

