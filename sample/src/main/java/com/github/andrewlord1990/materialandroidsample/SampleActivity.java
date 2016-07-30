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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.github.andrewlord1990.materialandroidsample.color.ColorChooserDialog;
import com.github.andrewlord1990.materialandroidsample.color.ColorSampleActivity;
import com.github.andrewlord1990.materialandroidsample.color.Colors;
import com.github.andrewlord1990.materialandroidsample.components.ComponentsSampleActivity;
import com.github.andrewlord1990.materialandroidsample.typography.TypographySampleActivity;

import java.util.ArrayList;

public class SampleActivity extends BaseSampleActivity implements ColorChooserDialog.Listener {

  private static final String STATE_PRIMARY_COLOR = "statePrimaryColor";
  private static final String STATE_ACCENT_COLOR = "stateAccentColor";

  private static final int REQUEST_PRIMARY_COLOR = 0;
  private static final int REQUEST_ACCENT_COLOR = 1;

  private int primaryColor;
  private int accentColor;
  private View primaryColorSquare;
  private View accentColorSquare;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    restoreColorsFromState(savedInstanceState);
    initialiseColors();

    setContentView(R.layout.activity_sample);

    setTitle(R.string.app_name);
    setupColorSample();
    setupTypographySample();
    setupComponentsSample();
  }

  private void restoreColorsFromState(Bundle savedInstanceState) {
    primaryColor = savedInstanceState.getInt(STATE_PRIMARY_COLOR);
    accentColor = savedInstanceState.getInt(STATE_ACCENT_COLOR);
  }

  private void initialiseColors() {
    if (primaryColor == 0) {
      primaryColor = ContextCompat.getColor(this, R.color.md_cyan_500);
    }
    if (accentColor == 0) {
      accentColor = ContextCompat.getColor(this, R.color.md_red_a200);
    }
  }

  private void setupColorSample() {
    primaryColorSquare = findViewById(R.id.primary_color_square);
    if (primaryColorSquare != null) {
      refreshPrimaryColorSquare();
      primaryColorSquare.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          showColorChooser(
              REQUEST_PRIMARY_COLOR,
              R.string.sample_primary_color,
              Colors.getPrimaryColors(view.getContext()));
        }
      });
    }
    accentColorSquare = findViewById(R.id.accent_color_square);
    refreshAccentColorSquare();
    accentColorSquare.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        showColorChooser(
            REQUEST_ACCENT_COLOR,
            R.string.sample_accent_color,
            Colors.getAccentColors(view.getContext()));
      }
    });
    Button colorSample = (Button) findViewById(R.id.color_sample_button);
    if (colorSample != null) {
      colorSample.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          onColorSampleSelected();
        }
      });
    }
  }

  private void showColorChooser(int requestCode, @StringRes int title, ArrayList<Integer> colors) {
    Bundle args = new Bundle();
    args.putInt(ColorChooserDialog.ARG_REQUEST_CODE, requestCode);
    args.putString(ColorChooserDialog.ARG_TITLE, getString(title));
    args.putIntegerArrayList(ColorChooserDialog.ARG_COLORS, colors);
    ColorChooserDialog dialog = new ColorChooserDialog();
    dialog.setArguments(args);
    dialog.show(getSupportFragmentManager(), ColorChooserDialog.TAG);
  }

  private void onColorSampleSelected() {
    Intent intent = new Intent(this, ColorSampleActivity.class);
    intent.putExtra(ColorSampleActivity.EXTRA_PRIMARY_COLOR, primaryColor);
    intent.putExtra(ColorSampleActivity.EXTRA_ACCENT_COLOR, accentColor);
    startActivity(intent);
  }

  @Override
  public void onColorSelected(int requestCode, int color) {
    switch (requestCode) {
      case REQUEST_PRIMARY_COLOR:
        primaryColor = color;
        refreshPrimaryColorSquare();
        break;
      case REQUEST_ACCENT_COLOR:
        accentColor = color;
        refreshAccentColorSquare();
        break;
      default:
        break;
    }
  }

  private void refreshPrimaryColorSquare() {
    primaryColorSquare.setBackgroundColor(primaryColor);
  }

  private void refreshAccentColorSquare() {
    accentColorSquare.setBackgroundColor(accentColor);
  }

  private void setupTypographySample() {
    Button button = (Button) findViewById(R.id.typography_sample_button);
    if (button != null) {
      button.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          startActivity(new Intent(SampleActivity.this, TypographySampleActivity.class));
        }
      });
    }
  }

  private void setupComponentsSample() {
    Button button = (Button) findViewById(R.id.components_sample_button);
    if (button != null) {
      button.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          startActivity(new Intent(SampleActivity.this, ComponentsSampleActivity.class));
        }
      });
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    outState.putInt(STATE_PRIMARY_COLOR, primaryColor);
    outState.putInt(STATE_ACCENT_COLOR, accentColor);
  }
}
