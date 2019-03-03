package com.example.share_food.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.share_food.R;
import com.example.share_food.model.Comment;

import java.util.List;

public class CommentAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Comment> arrItem;

    public CommentAdapter(Context context, int layout, List<Comment> arrItem) {
        this.context = context;
        this.layout = layout;
        this.arrItem = arrItem;
    }

    private class ViewHolder{
        TextView tvName;
        TextView tvText;
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
                holder.tvName = viewRow.findViewById(R.id.tvNameComment);
            }
            if (viewRow != null) {
                holder.tvText = viewRow.findViewById(R.id.tvCommentContent);
            }
            if (viewRow != null) {
                viewRow.setTag(holder);
            }
        }
        Comment comment = arrItem.get(i);
        ViewHolder holder = null;
        if (viewRow != null) {
            holder = (ViewHolder) viewRow.getTag();
        }
        if (holder != null) {
            holder.tvName.setText(comment.getName());
        }
        if (holder != null) {
            holder.tvText.setText(comment.getText());
        }

        return viewRow;
    }

}