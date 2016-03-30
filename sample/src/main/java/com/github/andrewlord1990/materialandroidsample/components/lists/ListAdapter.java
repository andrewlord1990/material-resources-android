package com.github.andrewlord1990.materialandroidsample.components.lists;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.andrewlord1990.materialandroidsample.R;

public class ListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private Context context;

    @LayoutRes
    private int layout = 0;

    private String primaryText;

    public ListAdapter(Context context, int listType) {
        this.context = context;
        layout = getLayout(listType);
        primaryText = getPrimaryText(listType);
    }

    @LayoutRes
    private int getLayout(int listType) {
        int[] layouts = new int[]{
                R.layout.md_list_single_line,
                R.layout.md_list_single_line_icon,
                R.layout.md_list_single_line_avatar,
                R.layout.md_list_single_line_avatar_and_icon,
                R.layout.md_list_two_line,
                R.layout.md_list_two_line_icon,
                R.layout.md_list_two_line_avatar,
                R.layout.md_list_two_line_avatar_and_icon,
                R.layout.md_list_three_line,
                R.layout.md_list_three_line_icon,
                R.layout.md_list_three_line_avatar,
                R.layout.md_list_three_line_avatar_and_icon
        };
        return listType < layouts.length ? layouts[listType] : layouts[0];
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
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
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

        private TextView primaryTextView;
        private TextView secondaryTextView;
        private TextView tertiaryTextView;
        private ImageView iconView;
        private ImageView avatarView;

        public ListViewHolder(View itemView, String primaryText) {
            super(itemView);

            primaryTextView = (TextView) itemView.findViewById(R.id.list_primary_text);
            if (primaryTextView != null) {
                primaryTextView.setText(primaryText);
            }
            secondaryTextView = (TextView) itemView.findViewById(R.id.list_secondary_text);
            if (secondaryTextView != null) {
                secondaryTextView.setText(R.string.sample_lists_secondary);
            }
            tertiaryTextView = (TextView) itemView.findViewById(R.id.list_tertiary_text);
            if (tertiaryTextView != null) {
                tertiaryTextView.setText(R.string.sample_lists_tertiary);
            }
            iconView = (ImageView) itemView.findViewById(R.id.list_icon);
            if (iconView != null) {
                iconView.setImageResource(R.drawable.ic_star);
            }
            avatarView = (ImageView) itemView.findViewById(R.id.list_avatar);
            if (avatarView != null) {
                avatarView.setImageResource(R.drawable.ic_avatar);
            }
        }
    }

    public static class HeaderViewHolder extends ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
