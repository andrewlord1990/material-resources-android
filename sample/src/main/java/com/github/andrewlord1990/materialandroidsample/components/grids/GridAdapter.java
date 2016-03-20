/*
 *  Copyright (C) 2016 Andrew Lord
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package com.github.andrewlord1990.materialandroidsample.components.grids;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.github.andrewlord1990.materialandroidsample.R;
import com.github.andrewlord1990.materialandroidsample.components.grids.GridAdapter.GridViewHolder;
import com.squareup.picasso.Picasso;

public class GridAdapter extends RecyclerView.Adapter<GridViewHolder> {

    private static final int TYPE_FOOTER = 0;
    private static final int TYPE_HEADER = 1;

    private static int type = TYPE_FOOTER;

    private Context context;

    @LayoutRes
    private int layout = 0;

    private String primaryText;

    private String[] images;

    public GridAdapter(Context context, int gridType) {
        this.context = context;
        layout = getLayout(gridType);
        primaryText = getPrimaryText(gridType);
        images = context.getResources().getStringArray(R.array.grid_sample_images);
    }

    @LayoutRes
    private int getLayout(int gridType) {
        int[] layouts = new int[]{
                R.layout.grid_list_label_single_line,
                R.layout.grid_list_label_single_line_icon_start,
                R.layout.grid_list_label_single_line_icon_end,
                R.layout.grid_list_label_two_line_same,
                R.layout.grid_list_label_two_line_different,
                R.layout.grid_list_label_two_line_same_icon_start,
                R.layout.grid_list_label_two_line_same_icon_end,
                R.layout.grid_list_label_two_line_different_icon_start,
                R.layout.grid_list_label_two_line_different_icon_end
        };
        return gridType < layouts.length ? layouts[gridType] : layouts[0];
    }

    private String getPrimaryText(int listType) {
        @StringRes int stringRes = R.string.sample_grids_single_item;
        if (listType >= 3) {
            stringRes = R.string.sample_grids_two_item_primary;
        }
        return context.getString(stringRes);
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.grid_list_cell, parent, false);
        ViewGroup labelView = (ViewGroup) view.findViewById(R.id.label);
        inflater.inflate(layout, labelView);
        setupLabel(labelView);
        return new GridViewHolder(view, primaryText);
    }

    private void setupLabel(ViewGroup labelView) {
        RelativeLayout.LayoutParams params = (LayoutParams) labelView.getLayoutParams();
        if (type == TYPE_FOOTER) {
            params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.image);
            params.addRule(RelativeLayout.ALIGN_TOP, 0);
        } else if (type == TYPE_HEADER) {
            params.addRule(RelativeLayout.ALIGN_TOP, R.id.image);
            params.addRule(RelativeLayout.ALIGN_BOTTOM, 0);
        }
        labelView.setLayoutParams(params);
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {
        String url = images[position];
        Picasso.with(context)
                .load(url)
                .fit()
                .into(holder.imageView);
        setupLabel(holder.labelView);
    }

    public void toggleType() {
        type = type == TYPE_HEADER ? TYPE_FOOTER : TYPE_HEADER;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class GridViewHolder extends ViewHolder {

        private ImageView imageView;
        private ViewGroup labelView;
        private TextView primaryTextView;
        private TextView secondaryTextView;
        private ImageView iconView;

        public GridViewHolder(View itemView, String primaryText) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image);
            primaryTextView = (TextView) itemView.findViewById(R.id.grid_list_label_line_1);
            if (primaryTextView != null) {
                primaryTextView.setText(primaryText);
                primaryTextView.setTextColor(getColor(R.color.md_light_primary_text_100));
            }
            secondaryTextView = (TextView) itemView.findViewById(R.id.grid_list_label_line_2);
            if (secondaryTextView != null) {
                secondaryTextView.setText(R.string.sample_lists_secondary);
                secondaryTextView.setTextColor(getColor(R.color.md_light_primary_text_100));
            }
            iconView = (ImageView) itemView.findViewById(R.id.grid_list_label_icon);
            if (iconView != null) {
                iconView.setImageResource(R.drawable.ic_star_white);
            }
            labelView = (ViewGroup) itemView.findViewById(R.id.label);
        }

        private int getColor(@ColorRes int colorRes) {
            return ContextCompat.getColor(itemView.getContext(), colorRes);
        }
    }
}
