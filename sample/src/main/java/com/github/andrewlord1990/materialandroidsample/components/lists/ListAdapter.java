package com.github.andrewlord1990.materialandroidsample.components.lists;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.github.andrewlord1990.materialandroid.component.list.ListItemView;
import com.github.andrewlord1990.materialandroid.component.list.ListItemView.Variant;
import com.github.andrewlord1990.materialandroidsample.R;

public class ListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private Context context;

    private int listType;

    private String primaryText;

    public ListAdapter(Context context, @Variant int listType) {
        this.context = context;
        this.listType = listType;
        primaryText = getPrimaryText(listType);
    }

    private String getPrimaryText(int listType) {
        @StringRes int stringRes = R.string.sample_lists_single_item;
        if (listType >= 4 && listType < 8) {
            stringRes = R.string.sample_lists_two_item_primary;
        } else if (listType >= 8) {
            stringRes = R.string.sample_lists_three_item_primary;
        }
        return context.getString(stringRes);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return createHeader(parent);
        }
        ListItemView view = new ListItemView(parent.getContext());
        LayoutParams params = new RecyclerView.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        view.setVariant(listType);
        return new ListViewHolder(view, primaryText);
    }

    private ViewHolder createHeader(ViewGroup parent) {
        TextView view = (TextView) LayoutInflater.from(context).inflate(
                R.layout.md_subheader, parent, false);
        view.setText(R.string.sample_lists_subheader);
        view.setTextColor(ContextCompat.getColor(context, R.color.md_red_a200));
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
            if (shouldHighlightItem(position)) {
                int color = ContextCompat.getColor(context, R.color.alternate_list_item_background);
                holder.itemView.setBackgroundColor(color);
            } else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    private boolean shouldHighlightItem(int position) {
        return position % 2 == 0;
    }

    @Override
    public int getItemCount() {
        return 24;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 6 == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    public static class ListViewHolder extends ViewHolder {

        public ListViewHolder(ListItemView itemView, String primaryText) {
            super(itemView);

            itemView.setTextPrimary(primaryText);
            itemView.setTextSecondary(R.string.sample_lists_secondary);
            itemView.setTextTertiary(R.string.sample_lists_tertiary);
            itemView.setIcon(R.drawable.ic_star);
            itemView.setAvatar(R.drawable.ic_avatar);
        }
    }

    public static class HeaderViewHolder extends ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
