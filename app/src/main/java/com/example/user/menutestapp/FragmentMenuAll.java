package com.example.user.menutestapp;

import com.example.user.menutestapp.RecyclerViewMenuAll.Adapter.MenuAdapter;
import com.example.user.menutestapp.RecyclerViewMenuAll.MenuItems.ExternalItem;
import com.example.user.menutestapp.RecyclerViewMenuAll.MenuItems.Item;
import com.example.user.menutestapp.RecyclerViewMenuAll.MenuItems.NestedItem;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

import java.util.ArrayList;

public class FragmentMenuAll extends Fragment {

    final static String FRAG_SAVE = "FRAGMENT_SAVE_STATE";

    RecyclerView mRecyclerView;
    MenuAdapter mAdapter;
    Context mContext;
    FragmentSaveListState fragmentSaveAllMenuState;
    int rotate;

    @Override
    public void onPause() {
        fragmentSaveAllMenuState.setListState(mAdapter.menuList);
        fragmentSaveAllMenuState.setOpenItemList(mAdapter.getOpenItemList());
        fragmentSaveAllMenuState.setRotation(rotate);
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragment_view = inflater.inflate(R.layout.fragment_all, null);
        mContext = getActivity().getApplicationContext();
        mRecyclerView = (RecyclerView) fragment_view.findViewById(R.id.menu_recycler_all);

        fragmentSaveAllMenuState = (FragmentSaveListState) getFragmentManager().findFragmentByTag(FRAG_SAVE);

        rotate = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        if((fragmentSaveAllMenuState != null) && (fragmentSaveAllMenuState.getRotation() != rotate)) {
            mAdapter = new MenuAdapter(mContext, fragmentSaveAllMenuState.getListState());
            mAdapter.setOpenItemList(fragmentSaveAllMenuState.getOpenItemList());
            mAdapter.externalList = getMenuList();
        } else {
            fragmentSaveAllMenuState = new FragmentSaveListState();
            fragmentSaveAllMenuState.setRotation(rotate);
            getFragmentManager().beginTransaction()
                    .add(fragmentSaveAllMenuState, FRAG_SAVE)
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
        ExternalItem extItem2 = new ExternalItem("Суши и Роллы", "02");
        ExternalItem extItem3 = new ExternalItem("Паста", "03");

        externalList.add(extItem1);
        externalList.add(extItem2);
        externalList.add(extItem3);

        NestedItem nestedItem11 = new NestedItem("Суши классические");
        NestedItem nestedItem12 = new NestedItem("Суши запечёные");
        NestedItem nestedItem13 = new NestedItem("Роллы классические");
        NestedItem nestedItem14 = new NestedItem("Роллы запечёные");
        NestedItem nestedItem15 = new NestedItem("Наборы");

        extItem2.addNestedItem(nestedItem11);
        extItem2.addNestedItem(nestedItem12);
        extItem2.addNestedItem(nestedItem13);
        extItem2.addNestedItem(nestedItem14);
        extItem2.addNestedItem(nestedItem15);

        return externalList;
    }

}
