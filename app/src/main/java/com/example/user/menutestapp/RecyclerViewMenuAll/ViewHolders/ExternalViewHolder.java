package com.example.user.menutestapp.RecyclerViewMenuAll.ViewHolders;

import com.example.user.menutestapp.R;
import com.example.user.menutestapp.RecyclerViewMenuAll.Adapter.MenuAdapter;
import com.example.user.menutestapp.RecyclerViewMenuAll.MenuItems.Item;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 04.07.2016.
 */
public class ExternalViewHolder extends RecyclerView.ViewHolder {
    public TextView tvExternalData;
    public TextView tvExternalNumber;
    public ImageButton externalArrow;
    private ArrayList<Item> mNestedList;

    public ExternalViewHolder(final View viewItem) {
        super(viewItem);
        tvExternalData = (TextView) viewItem.findViewById(R.id.external_data);
        tvExternalNumber = (TextView) viewItem.findViewById(R.id.external_number);
        externalArrow = (ImageButton) viewItem.findViewById(R.id.external_arrow);
        mNestedList = new ArrayList<Item>();

        viewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MenuAdapter.mlistener != null) {
                    MenuAdapter.mlistener.onItemClick(viewItem, getLayoutPosition());
                }
            }
        });
    }
}
