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

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridListSpacingItemDecoration extends RecyclerView.ItemDecoration {

  private int spacing;

  public GridListSpacingItemDecoration(int spacingPixels) {
    spacing = spacingPixels;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();

    int spanCount = layoutManager.getSpanCount();
    int position = parent.getChildAdapterPosition(view);
    int column = position % spanCount;

    outRect.left = column * spacing / spanCount;
    outRect.right = spacing - (column + 1) * spacing / spanCount;
    if (position >= spanCount) {
      outRect.top = spacing;
    }
  }
}
