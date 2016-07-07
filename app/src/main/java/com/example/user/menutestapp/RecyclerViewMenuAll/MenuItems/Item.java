package com.example.user.menutestapp.RecyclerViewMenuAll.MenuItems;

import java.util.ArrayList;

public interface Item {
    public int getTypeItem();
    public String getItemData();
    public ArrayList<Item> getNestedList();
}
