package com.example.share_food.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.share_food.R;
import com.example.share_food.model.ItemService;

import java.util.List;

public class ItemDrawerLayout extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ItemService> arrItem;

    public ItemDrawerLayout(Context context, int layout, List<ItemService> arrItem) {
        this.context = context;
        this.layout = layout;
        this.arrItem = arrItem;
    }

    private class ViewHolder{
        ImageView img;
        TextView tvName;
    }

    @Override
    public int getCount() {
        return arrItem.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewRow = view;
        if(viewRow == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                viewRow = inflater.inflate(layout,viewGroup,false);
            }

            ViewHolder holder = new ViewHolder();
            if (viewRow != null) {
                holder.tvName = viewRow.findViewById(R.id.tvNameCategory);
            }
            if (viewRow != null) {
                holder.img = viewRow.findViewById(R.id.imgItemService);
            }
            if (viewRow != null) {
                viewRow.setTag(holder);
            }
        }
        ItemService itemService = arrItem.get(i);
        ViewHolder holder = null;
        if (viewRow != null) {
            holder = (ViewHolder) viewRow.getTag();
        }
        if (holder != null) {
            holder.tvName.setText(itemService.getName());
        }
        if (holder != null) {
            holder.img.setImageResource(itemService.getImage());
        }

        return viewRow;
    }

}