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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.github.andrewlord1990.materialandroidsample.R;

import java.util.List;

public class ColorChooserDialog extends DialogFragment {

  public static final String TAG = "ColorChooserDialog";

  public static final String ARG_REQUEST_CODE = "argRequestCode";
  public static final String ARG_TITLE = "argTitle";
  public static final String ARG_COLORS = "argColors";

  private int requestCode;
  private String title;
  private List<Integer> colors;

  private Listener listener;

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);

    listener = (Listener) activity;
  }

  @Override
  public void onDetach() {
    super.onDetach();

    listener = null;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    requestCode = getArguments().getInt(ARG_REQUEST_CODE);
    title = getArguments().getString(ARG_TITLE);
    colors = getArguments().getIntegerArrayList(ARG_COLORS);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog dialog = new AlertDialog.Builder(getContext())
        .setTitle(title)
        .setPositiveButton(R.string.sample_color_chooser_cancel, new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
          }
        })
        .create();
    dialog.setView(setupCustomView(dialog));
    return dialog;
  }

  private View setupCustomView(final AlertDialog dialog) {
    Context context = dialog.getContext();
    View customView = LayoutInflater.from(context)
        .inflate(R.layout.color_chooser, null);
    GridView grid = (GridView) customView.findViewById(R.id.colorChooserGrid);
    ColorGridAdapter adapter = new ColorGridAdapter(context, colors);
    grid.setAdapter(adapter);
    grid.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null) {
          listener.onColorSelected(requestCode, colors.get(position));
        }
        dialog.dismiss();
      }
    });
    return customView;
  }

  public interface Listener {
    void onColorSelected(int requestCode, int color);
  }
}
