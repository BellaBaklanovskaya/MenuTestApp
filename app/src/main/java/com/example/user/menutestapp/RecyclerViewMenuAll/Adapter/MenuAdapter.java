package com.example.user.menutestapp.RecyclerViewMenuAll.Adapter;

import com.example.user.menutestapp.R;
import com.example.user.menutestapp.RecyclerViewMenuAll.MenuItems.ExternalItem;
import com.example.user.menutestapp.RecyclerViewMenuAll.MenuItems.Item;
import com.example.user.menutestapp.RecyclerViewMenuAll.MenuItems.NestedItem;
import com.example.user.menutestapp.RecyclerViewMenuAll.ViewHolders.ExternalViewHolder;
import com.example.user.menutestapp.RecyclerViewMenuAll.ViewHolders.NestedViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by User on 04.07.2016.
 */
public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int EXTERNAL_TYPE = 0;
    private static final int NESTED_TYPE = 1;

    LayoutInflater mLayoutInflater;
    public ArrayList<Item> menuList;
    public ArrayList<Item> externalList;
    private LinkedList<ExternalItem> openItemList;

    public static OnItemClickListener mlistener;

    public interface OnItemClickListener {
        void onItemClick(View viewItem, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {

        this.mlistener = listener;
    }

    public MenuAdapter(Context context, ArrayList<Item> itemList) {
        super();

        mLayoutInflater = LayoutInflater.from(context);
        menuList = new ArrayList<Item>();
        externalList = itemList;
        openItemList = new LinkedList<ExternalItem>();

        generateMenuList();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Item itemList  = menuList.get(position);

        if(itemList instanceof ExternalItem) {
            ExternalViewHolder externalVH = (ExternalViewHolder) viewHolder;

            ExternalItem externalItem = (ExternalItem) itemList;
            onBindExternalViewHolder(externalVH, position, externalItem);
        } else {
            if(itemList == null) {
                throw new IllegalStateException("Incorrect ViewHolder found");
            } else {
                onBindNestedViewHolder((NestedViewHolder)viewHolder, position, (NestedItem) itemList);
            }
        }
    }

    public void onBindExternalViewHolder(ExternalViewHolder externalVH, int position, ExternalItem externalItem) {
        externalVH.tvExternalData.setText(externalItem.getItemData());
        externalVH.tvExternalNumber.setText(externalItem.getItemNumber());
        externalVH.externalArrow.setRotation(externalItem.getRotateImageButton());
    }

    public void onBindNestedViewHolder(NestedViewHolder nestedVH, int position, NestedItem nestedItem) {
        nestedVH.tvNestedData.setText(nestedItem.getItemData());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(viewType == EXTERNAL_TYPE) {
            return onCreateExternalViewHolder(viewGroup);
        } else {
            if(viewType == NESTED_TYPE) {
                return  onCreateNestedViewHolder(viewGroup);
            } else {
                throw new IllegalStateException("Incorrect ViewType found");
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        Item listItem = menuList.get(position);
        if (listItem instanceof ExternalItem) {
            return EXTERNAL_TYPE;
        } else if (listItem == null) {
            throw new IllegalStateException("Null object added");
        } else {
            return NESTED_TYPE;
        }
    }

    public ExternalViewHolder onCreateExternalViewHolder(ViewGroup viewGroup) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_external_item, viewGroup, false);
        return new ExternalViewHolder(itemView);
    }

    public NestedViewHolder onCreateNestedViewHolder(ViewGroup viewGroup) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_nested_item, viewGroup, false);
        return new NestedViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public Object getItem(int position) {
        return menuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private void generateMenuList() {
        menuList.clear();
        generateList(externalList);
    }

    private void generateList(ArrayList<Item> itemList) {
        for (Item item : itemList) {
            menuList.add(item);
            if (openItemList.contains(item))
                generateList(item.getNestedList());
        }
    }
    public void clickOnItem (int position) {
        Item item = menuList.get(position);
        if(item instanceof ExternalItem) {
            if (!openItemList.remove(item)) {
                openItemList.add((ExternalItem) item);
                ((ExternalItem) item).setRotateImageButton(0);
            } else {
                ((ExternalItem) item).setRotateImageButton(-90);
            }
            generateMenuList();
            notifyDataSetChanged();
        } else { return; }
    }

    public void setOpenItemList(LinkedList<ExternalItem> openList) {
        openItemList = openList;
    }


    public LinkedList<ExternalItem> getOpenItemList() {
        return openItemList;
    }
}
