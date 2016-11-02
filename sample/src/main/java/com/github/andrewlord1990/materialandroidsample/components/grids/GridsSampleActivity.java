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

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.github.andrewlord1990.materialandroid.component.grid.GridItemView;
import com.github.andrewlord1990.materialandroidsample.BaseSampleActivity;
import com.github.andrewlord1990.materialandroidsample.R;

public class GridsSampleActivity extends BaseSampleActivity {

  public static final String EXTRA_GRID_TYPE = "extraGridType";

  private int gridType;
  private GridAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    gridType = getIntent().getIntExtra(EXTRA_GRID_TYPE, 0);

    setContentView(R.layout.activity_grids_sample);

    setupToolbar();
    setupList();
  }

  private void setupToolbar() {
    setTitle(R.string.sample_grids_title);
    showUpButton();
  }

  private void setupList() {
    RecyclerView list = (RecyclerView) findViewById(R.id.recyclerView);
    if (list != null) {
      int cellSize = getResources().getDimensionPixelSize(R.dimen.grid_cell_size);
      list.setLayoutManager(new GridAutoFitLayoutManager(this, cellSize));
      list.setHasFixedSize(true);
      adapter = new GridAdapter(this, gridType);
      list.setAdapter(adapter);
      int spacing = getResources().getDimensionPixelSize(R.dimen.ma_grid_list_cell_spacing_small);
      list.addItemDecoration(new GridListSpacingItemDecoration(spacing));
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_sample_grids, menu);
    menu.findItem(R.id.action_icon_gravity)
        .setVisible(shouldShowIconGravityAction());
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_icon_gravity:
        adapter.toggleIconGravity();
        return true;
      case R.id.action_label_position:
        adapter.toggleType();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private boolean shouldShowIconGravityAction() {
    return gridType == GridItemView.VARIANT_ONE_LINE_TEXT_ICON
        || gridType == GridItemView.VARIANT_TWO_LINE_TEXT_ICON;
  }

}
