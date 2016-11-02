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
import android.widget.TextView;

import com.github.andrewlord1990.materialandroid.component.list.ListItemView;
import com.github.andrewlord1990.materialandroid.component.list.ListItemView.ListItemVariant;
import com.github.andrewlord1990.materialandroidsample.R;

public class ListAdapter extends RecyclerView.Adapter<ViewHolder> {

  private static final int TYPE_HEADER = 0;
  private static final int TYPE_ITEM = 1;

  private final Context context;
  private final int listType;
  private final String primaryText;

  public ListAdapter(Context context, @ListItemVariant int listType) {
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
    ListItemView view = (ListItemView) LayoutInflater.from(context)
        .inflate(R.layout.list_row, parent, false);
    if (listType != ListItemView.VARIANT_ONE_LINE_TEXT) {
      view.setVariant(listType);
    }
    view.setPrimaryText(primaryText);
    return new ListViewHolder(view);
  }

  private ViewHolder createHeader(ViewGroup parent) {
    TextView view = (TextView) LayoutInflater.from(context).inflate(
        R.layout.ma_subheader, parent, false);
    view.setText(R.string.sample_lists_subheader);
    view.setTextColor(ContextCompat.getColor(context, R.color.ma_red_a200));
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

    public ListViewHolder(ListItemView itemView) {
      super(itemView);
    }
  }

  public static class HeaderViewHolder extends ViewHolder {

    public HeaderViewHolder(View itemView) {
      super(itemView);
    }
  }
}
