package com.example.share_food.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.share_food.R;
import com.example.share_food.model.Category;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Category> arrItem;

    public CategoryAdapter(Context context, int layout, List<Category> arrItem) {
        this.context = context;
        this.layout = layout;
        this.arrItem = arrItem;
    }

    private class ViewHolder{
        TextView tvTitle;
        ImageView imView;
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
                holder.tvTitle = viewRow.findViewById(R.id.tvItemCategory);
            }
            if (viewRow != null) {
                holder.imView = viewRow.findViewById(R.id.imItemCategory);
            }

            if (viewRow != null) {
                viewRow.setTag(holder);
            }
        }
        Category item = arrItem.get(i);
        ViewHolder holder = null;
        if (viewRow != null) {
            holder = (ViewHolder) viewRow.getTag();
        }
        if (holder != null) {
            holder.tvTitle.setText(item.getName());
        }
        if (holder != null) {
            Glide.with(context).load(item.getImage()).into(holder.imView);
        }

        return viewRow;
    }
}