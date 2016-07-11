package com.example.user.menutestapp;

import android.app.Fragment;
import android.os.Bundle;

import com.example.user.menutestapp.RecyclerViewMenuAll.MenuItems.ExternalItem;
import com.example.user.menutestapp.RecyclerViewMenuAll.MenuItems.Item;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by User on 11.07.2016.
 */
public class FragmentSaveState extends Fragment {
    private ArrayList<Item> mListState;
    private LinkedList<ExternalItem> mOpenItemSave;
    private int mRotation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListState = new ArrayList<Item>();
        mOpenItemSave = new LinkedList<ExternalItem>();
        setRetainInstance(true);
    }

    public void setListState(ArrayList<Item> listState) {
        mListState = listState;
    }

    public ArrayList<Item> getListState() {
        return mListState;
    }

    public void setOpenItemList(LinkedList<ExternalItem> openItemList) {
        mOpenItemSave = openItemList;
    }

    public LinkedList<ExternalItem> getOpenItemList() {
        return mOpenItemSave;
    }

    public void setRotation(int rotation) {
        mRotation = rotation;
    }

    public int getRotation() {
        return mRotation;
    }
}
