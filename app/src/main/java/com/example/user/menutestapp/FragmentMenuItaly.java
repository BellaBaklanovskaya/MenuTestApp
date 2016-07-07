package com.example.user.menutestapp;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.menutestapp.RecyclerViewMenuAll.Adapter.MenuAdapter;
import com.example.user.menutestapp.RecyclerViewMenuAll.MenuItems.ExternalItem;
import com.example.user.menutestapp.RecyclerViewMenuAll.MenuItems.Item;
import com.example.user.menutestapp.RecyclerViewMenuAll.MenuItems.NestedItem;

import java.util.ArrayList;

public class FragmentMenuItaly extends Fragment {

    final static String FRAG_SAVE_ITALY = "FRAGMENT_SAVE_STATE_ITALY";

    RecyclerView mRecyclerView;
    MenuAdapter mAdapter;
    Context mContext;
    FragmentSaveListState fragmentSaveItalyMenuState;
    int rotate;

    @Override
    public void onPause() {
        fragmentSaveItalyMenuState.setListState(mAdapter.menuList);
        fragmentSaveItalyMenuState.setOpenItemList(mAdapter.getOpenItemList());
        fragmentSaveItalyMenuState.setRotation(rotate);
        super.onPause();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragment_view = inflater.inflate(R.layout.fragment_italy, null);
        mContext = getActivity().getApplicationContext();
        mRecyclerView = (RecyclerView) fragment_view.findViewById(R.id.menu_recycler_italy);


        fragmentSaveItalyMenuState = (FragmentSaveListState) getFragmentManager().findFragmentByTag(FRAG_SAVE_ITALY);

        rotate = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        if((fragmentSaveItalyMenuState != null) && (fragmentSaveItalyMenuState.getRotation() != rotate)) {
            mAdapter = new MenuAdapter(mContext, fragmentSaveItalyMenuState.getListState());
            mAdapter.setOpenItemList(fragmentSaveItalyMenuState.getOpenItemList());
            mAdapter.externalList = getMenuList();
        } else {
            fragmentSaveItalyMenuState = new FragmentSaveListState();
            fragmentSaveItalyMenuState.setRotation(rotate);
            getFragmentManager().beginTransaction()
                    .add(fragmentSaveItalyMenuState, FRAG_SAVE_ITALY)
                    .commit();
            mAdapter = new MenuAdapter(mContext, getMenuList());
        }

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position){
                mAdapter.clickOnItem(position);
            }
        });
        return fragment_view;
    }

    public ArrayList<Item> getMenuList() {
        ArrayList<Item> externalList = new ArrayList<Item>();

        ExternalItem extItem1 = new ExternalItem("Пицца и Пинца", "01");
        ExternalItem extItem2 = new ExternalItem("Паста", "02");

        externalList.add(extItem1);
        externalList.add(extItem2);

        NestedItem nestedItem21 = new NestedItem("Фетучини с белыми грибами");
        NestedItem nestedItem22 = new NestedItem("Спагетти с морепродуктами");
        NestedItem nestedItem23 = new NestedItem("Спагетти Болоньезе");

        NestedItem nestedItem11 = new NestedItem("Пицца 'Маргарита'");
        NestedItem nestedItem12 = new NestedItem("Пицца 'Четыре сыра'");

        extItem2.addNestedItem(nestedItem21);
        extItem2.addNestedItem(nestedItem22);
        extItem2.addNestedItem(nestedItem23);
        extItem1.addNestedItem(nestedItem11);
        extItem1.addNestedItem(nestedItem12);

        return externalList;
    }
}
