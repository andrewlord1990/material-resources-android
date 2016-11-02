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

package com.github.andrewlord1990.materialandroidsample.typography;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;

import com.github.andrewlord1990.materialandroidsample.BaseSampleActivity;
import com.github.andrewlord1990.materialandroidsample.R;

public class TypographySampleActivity extends BaseSampleActivity {

  private static final int STATE_DARK = 0;
  private static final int STATE_LIGHT = 1;

  private static int state;

  private View background;
  private View stateDark;
  private View stateLight;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_typography_sample);

    setupToolbar();
    setupStates();
    setupFab();
  }

  private void setupToolbar() {
    setTitle(R.string.sample_typography_title);
    showUpButton();
  }

  private void setupStates() {
    background = findViewById(R.id.background);
    stateDark = findViewById(R.id.state_dark);
    stateLight = findViewById(R.id.state_light);
    if (state == STATE_LIGHT) {
      setLightState();
    } else {
      setDarkState();
    }
  }

  private void setupFab() {
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    if (fab != null) {
      fab.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          toggleState();
        }
      });
    }
  }

  private void toggleState() {
    if (state == STATE_LIGHT) {
      setDarkState();
    } else {
      setLightState();
    }
  }

  private void setDarkState() {
    state = STATE_DARK;
    background.setBackgroundColor(ContextCompat.getColor(this, R.color.ma_white));
    stateDark.setVisibility(View.VISIBLE);
    stateLight.setVisibility(View.GONE);
  }

  private void setLightState() {
    state = STATE_LIGHT;
    background.setBackgroundColor(ContextCompat.getColor(this, R.color.ma_black));
    stateDark.setVisibility(View.GONE);
    stateLight.setVisibility(View.VISIBLE);
  }
}
