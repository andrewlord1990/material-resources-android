package com.github.andrewlord1990.materialandroidsample.components.lists;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.andrewlord1990.materialandroidsample.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private Context context;

    @LayoutRes
    private int layout = 0;

    public ListAdapter(Context context, int listType) {
        this.context = context;
        layout = getLayout(listType);
    }

    @LayoutRes
    private int getLayout(int listType) {
        int[] layouts = new int[]{
                R.layout.list_single_line,
                R.layout.list_single_line_icon,
                R.layout.list_single_line_avatar,
                R.layout.list_single_line_avatar_and_icon,
                R.layout.list_two_line,
                R.layout.list_two_line_icon,
                R.layout.list_two_line_avatar,
                R.layout.list_two_line_avatar_and_icon,
                R.layout.list_three_line,
                R.layout.list_three_line_icon,
                R.layout.list_three_line_avatar,
                R.layout.list_three_line_avatar_and_icon
        };
        return listType < layouts.length ? layouts[listType] : layouts[0];
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        if (!(position % 2 == 0)) {
            int color = ContextCompat.getColor(context, R.color.alternate_list_item_background);
            holder.itemView.setBackgroundColor(color);
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class ListViewHolder extends ViewHolder {

        public ListViewHolder(View itemView) {
            super(itemView);
        }
    }
}
