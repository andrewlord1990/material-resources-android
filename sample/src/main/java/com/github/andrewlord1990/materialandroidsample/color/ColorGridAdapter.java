/*
 * Copyright (C) 2016 Andrew Lord
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package com.github.andrewlord1990.materialandroidsample.color;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.github.andrewlord1990.materialandroidsample.R;

import java.util.List;

public class ColorGridAdapter extends BaseAdapter {

  private Context context;
  private List<Integer> colors;

  public ColorGridAdapter(Context context, List<Integer> colors) {
    this.context = context;
    this.colors = colors;
  }

  @Override
  public int getCount() {
    return colors.size();
  }

  @Override
  public Object getItem(int position) {
    return colors.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View cellView = convertView;
    if (cellView == null) {
      cellView = LayoutInflater.from(context).inflate(R.layout.color_chooser_cell, parent, false);
    }
    View colorSquare = cellView.findViewById(R.id.color_square);
    colorSquare.setBackgroundColor(colors.get(position));
    return cellView;
  }

}
