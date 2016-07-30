/*
 *  Copyright (C) 2016 Andrew Lord
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *  the License.
 *
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *  an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package com.github.andrewlord1990.materialandroidsample.components.grids;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.github.andrewlord1990.materialandroid.component.grid.GridItemView;
import com.github.andrewlord1990.materialandroid.component.grid.GridItemView.GridItemVariant;
import com.github.andrewlord1990.materialandroid.component.grid.GridItemView.IconGravity;
import com.github.andrewlord1990.materialandroidsample.R;
import com.github.andrewlord1990.materialandroidsample.components.grids.GridAdapter.GridViewHolder;
import com.squareup.picasso.Picasso;

public class GridAdapter extends RecyclerView.Adapter<GridViewHolder> {

  private static final int TYPE_FOOTER = 0;
  private static final int TYPE_HEADER = 1;

  private static int type = TYPE_FOOTER;

  @IconGravity private int iconGravity = GridItemView.ICON_GRAVITY_START;

  private final Context context;
  @GridItemVariant private final int variant;
  private final String primaryText;
  private final String[] images;

  public GridAdapter(Context context, @GridItemVariant int gridType) {
    this.context = context;
    variant = gridType;
    primaryText = getPrimaryText(gridType);
    images = context.getResources().getStringArray(R.array.grid_sample_images);
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
    GridItemView labelView = (GridItemView) view.findViewById(R.id.label);
    labelView.setVariant(variant);
    setupLabel(labelView);
    return new GridViewHolder(view, primaryText);
  }

  private void setupLabel(GridItemView labelView) {
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
    holder.labelView.setIconGravity(iconGravity);
  }

  public void toggleType() {
    type = type == TYPE_HEADER ? TYPE_FOOTER : TYPE_HEADER;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return images.length;
  }

  public void toggleIconGravity() {
    iconGravity = (iconGravity == GridItemView.ICON_GRAVITY_START)
        ? GridItemView.ICON_GRAVITY_END : GridItemView.ICON_GRAVITY_START;
    notifyDataSetChanged();
  }

  public static class GridViewHolder extends ViewHolder {

    private ImageView imageView;
    private GridItemView labelView;

    public GridViewHolder(View itemView, String primaryText) {
      super(itemView);

      imageView = (ImageView) itemView.findViewById(R.id.image);
      labelView = (GridItemView) itemView.findViewById(R.id.label);
      labelView.setPrimaryText(primaryText);
    }
  }
}
