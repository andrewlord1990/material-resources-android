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

package com.github.andrewlord1990.materialandroidsample.color;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.IntentCompat;

import com.github.andrewlord1990.materialandroidsample.R;

import java.util.ArrayList;

public final class Colors {

  private Colors() {
  }

  public static ArrayList<Integer> primaryColors(Context context) {
    ArrayList<Integer> primaryColors = new ArrayList<>();
    primaryColors.add(ContextCompat.getColor(context, R.color.md_red_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_pink_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_purple_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_deep_purple_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_indigo_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_blue_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_light_blue_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_cyan_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_teal_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_green_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_light_green_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_lime_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_yellow_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_amber_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_orange_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_deep_orange_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_brown_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_grey_500));
    primaryColors.add(ContextCompat.getColor(context, R.color.md_blue_grey_500));
    return primaryColors;
  }

  public static ArrayList<Integer> accentColors(Context context) {
    ArrayList<Integer> accentColors = new ArrayList<>();
    accentColors.add(ContextCompat.getColor(context, R.color.md_red_a200));
    accentColors.add(ContextCompat.getColor(context, R.color.md_pink_a200));
    accentColors.add(ContextCompat.getColor(context, R.color.md_purple_a200));
    accentColors.add(ContextCompat.getColor(context, R.color.md_deep_purple_a200));
    accentColors.add(ContextCompat.getColor(context, R.color.md_indigo_a200));
    accentColors.add(ContextCompat.getColor(context, R.color.md_blue_a200));
    accentColors.add(ContextCompat.getColor(context, R.color.md_light_blue_a200));
    accentColors.add(ContextCompat.getColor(context, R.color.md_cyan_a200));
    accentColors.add(ContextCompat.getColor(context, R.color.md_teal_a200));
    accentColors.add(ContextCompat.getColor(context, R.color.md_green_a200));
    accentColors.add(ContextCompat.getColor(context, R.color.md_light_green_a200));
    accentColors.add(ContextCompat.getColor(context, R.color.md_lime_a200));
    accentColors.add(ContextCompat.getColor(context, R.color.md_yellow_a200));
    accentColors.add(ContextCompat.getColor(context, R.color.md_amber_a200));
    accentColors.add(ContextCompat.getColor(context, R.color.md_orange_a200));
    accentColors.add(ContextCompat.getColor(context, R.color.md_deep_orange_a200));
    return accentColors;
  }

}
