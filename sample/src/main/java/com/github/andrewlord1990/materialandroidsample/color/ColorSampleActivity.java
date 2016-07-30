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

import android.os.Bundle;
import android.view.View;

import com.github.andrewlord1990.materialandroidsample.BaseSampleActivity;
import com.github.andrewlord1990.materialandroidsample.R;

public class ColorSampleActivity extends BaseSampleActivity {

  public static final String EXTRA_PRIMARY_COLOR = "extraPrimaryColor";
  public static final String EXTRA_ACCENT_COLOR = "extraAccentColor";

  private int primaryColor;
  private int accentColor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    primaryColor = getIntent().getIntExtra(EXTRA_PRIMARY_COLOR, 0);
    accentColor = getIntent().getIntExtra(EXTRA_ACCENT_COLOR, 0);

    setContentView(R.layout.activity_color_sample);

    setupToolbar();
    setupViews();
  }

  private void setupToolbar() {
    toolbar.setBackgroundColor(primaryColor);
    setTitle(R.string.sample_color_title);
    showUpButton();
  }

  private void setupViews() {
    View button = findViewById(R.id.button);
    if (button != null) {
      button.setBackgroundColor(accentColor);
    }
  }

}
