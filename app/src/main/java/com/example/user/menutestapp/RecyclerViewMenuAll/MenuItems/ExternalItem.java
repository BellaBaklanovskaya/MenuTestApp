package com.example.user.menutestapp.RecyclerViewMenuAll.MenuItems;

import java.util.ArrayList;

public class ExternalItem implements Item {
    private String mItemData;
    private String mItemNumber;
    private ArrayList<Item> mNestedList;
    private int typeItem;
    private float mRotateImageButton;

    public ExternalItem(String itemData, String itemNumber) {
        this.mItemData = itemData;
        this.mItemNumber = itemNumber;
        this.mRotateImageButton = -90;
        typeItem = 0;
        mNestedList = new ArrayList<Item>();
    }

    @Override
    public int getTypeItem() {
        return typeItem;
    }

    public void setTextData(String textData) {
        mItemData = textData;
    }

    public String getItemNumber() {
        return mItemNumber;
    }

    public void setRotateImageButton(float rotate) { mRotateImageButton = rotate; }

    public float getRotateImageButton() { return mRotateImageButton; }

    @Override
    public String getItemData() {
        return mItemData;
    }

    @Override
    public ArrayList<Item> getNestedList() {
        return mNestedList;
    }

    public int getNestedCount() {
        return mNestedList.size();
    }

    public void addNestedItem(Item nested) {
        mNestedList.add(nested);
    }
}
