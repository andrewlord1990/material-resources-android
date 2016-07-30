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

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
    setupFab();
    setupList();
    setupIconGravityButton();
    setupTextSizesButton();
  }

  private void setupToolbar() {
    setTitle(R.string.sample_grids_title);
    showUpButton();
  }

  private void setupFab() {
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    if (fab != null) {
      fab.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          adapter.toggleType();
        }
      });
    }
  }

  private void setupList() {
    RecyclerView list = (RecyclerView) findViewById(R.id.recyclerView);
    if (list != null) {
      int cellSize = getResources().getDimensionPixelSize(R.dimen.grid_cell_size);
      list.setLayoutManager(new GridAutoFitLayoutManager(this, cellSize));
      list.setHasFixedSize(true);
      adapter = new GridAdapter(this, gridType);
      list.setAdapter(adapter);
      int spacing = getResources().getDimensionPixelSize(R.dimen.md_grid_list_cell_spacing_small);
      list.addItemDecoration(new GridListSpacingItemDecoration(spacing));
    }
  }

  private void setupIconGravityButton() {
    Button iconGravityButton = (Button) findViewById(R.id.iconGravity);
    if (iconGravityButton != null) {
      iconGravityButton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          adapter.toggleIconGravity();
        }
      });
    }
  }

  private void setupTextSizesButton() {
    Button textSizesButton = (Button) findViewById(R.id.textSizes);
    if (textSizesButton != null) {
      textSizesButton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {

        }
      });
    }
  }

}
