package com.example.user.menutestapp.RecyclerViewMenuAll.MenuItems;

import java.util.ArrayList;

/**
 * Created by User on 04.07.2016.
 */
public class NestedItem implements Item {
    public String mItemData;
    public int typeItem;

    public NestedItem(String itemData) {
        this.mItemData = itemData;
        this.typeItem = 1;
    }

    @Override
    public String getItemData() {
        return mItemData;
    }

    @Override
    public int getTypeItem() {
        return typeItem;
    }

    @Override
    public ArrayList<Item> getNestedList() { return null; }
}
