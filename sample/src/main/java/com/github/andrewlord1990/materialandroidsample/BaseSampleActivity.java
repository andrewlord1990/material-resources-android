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

package com.github.andrewlord1990.materialandroidsample;

import android.support.annotation.LayoutRes;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class BaseSampleActivity extends AppCompatActivity {

  protected Toolbar toolbar;

  @Override
  public void setContentView(@LayoutRes int layoutResId) {
    super.setContentView(layoutResId);

    setContentView();
  }

  @Override
  public void setContentView(View view) {
    super.setContentView(view);

    setContentView();
  }

  @Override
  public void setContentView(View view, LayoutParams params) {
    super.setContentView(view, params);

    setContentView();
  }

  protected void setContentView() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      setSupportActionBar(toolbar);
    }
  }

  protected void showUpButton() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onUpPressed();
      return true;
    }
    return false;
  }

  public void onUpPressed() {
    NavUtils.navigateUpFromSameTask(this);
  }

}
