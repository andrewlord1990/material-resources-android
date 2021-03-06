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

package com.github.andrewlord1990.materialandroidsample.components;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;

import com.github.andrewlord1990.materialandroidsample.BaseSampleActivity;
import com.github.andrewlord1990.materialandroidsample.R;
import com.github.andrewlord1990.materialandroidsample.components.grids.GridsSampleActivity;
import com.github.andrewlord1990.materialandroidsample.components.lists.ListsSampleActivity;

public class ComponentsSampleActivity extends BaseSampleActivity {

  private static int lastListType = 0;
  private static int lastGridType = 0;

  private Spinner listsSpinner;
  private Spinner gridsSpinner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_components_sample);

    setupToolbar();
    setupListsButton();
    setupGridsButton();
    setupTextFieldsButton();
    setupPasswordButton();
  }

  @Override
  protected void onResume() {
    super.onResume();

    listsSpinner.setSelection(lastListType);
    gridsSpinner.setSelection(lastGridType);
  }

  private void setupToolbar() {
    setTitle(R.string.sample_components_title);
    showUpButton();
  }

  private void setupListsButton() {
    listsSpinner = (Spinner) findViewById(R.id.list_types);
    if (listsSpinner != null) {
      listsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
          lastListType = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
          // Not needed
        }
      });
    }
    Button listsButton = (Button) findViewById(R.id.lists);
    if (listsButton != null) {
      listsButton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          onListsButtonPressed();
        }
      });
    }
  }

  private void onListsButtonPressed() {
    Intent intent = new Intent(this, ListsSampleActivity.class);
    intent.putExtra(ListsSampleActivity.EXTRA_LIST_TYPE, lastListType);
    startActivity(intent);
  }

  private void setupGridsButton() {
    gridsSpinner = (Spinner) findViewById(R.id.grid_types);
    if (gridsSpinner != null) {
      gridsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
          lastGridType = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
          // Not needed
        }
      });
    }
    Button gridsButton = (Button) findViewById(R.id.grids);
    if (gridsButton != null) {
      gridsButton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          onGridsButtonPressed();
        }
      });
    }
  }

  private void onGridsButtonPressed() {
    Intent intent = new Intent(this, GridsSampleActivity.class);
    intent.putExtra(GridsSampleActivity.EXTRA_GRID_TYPE, lastGridType);
    startActivity(intent);
  }

  private void setupTextFieldsButton() {
    Button textFields = (Button) findViewById(R.id.textFields);
    if (textFields != null) {
      textFields.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          onTextFieldsButtonPressed();
        }
      });
    }
  }

  private void onTextFieldsButtonPressed() {
    Intent intent = new Intent(this, TextFieldsSampleActivity.class);
    startActivity(intent);
  }

  private void setupPasswordButton() {
    Button password = (Button) findViewById(R.id.password);
    if (password != null) {
      password.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          onPasswordButtonPressed();
        }
      });
    }
  }

  private void onPasswordButtonPressed() {
    Intent intent = new Intent(this, PasswordFieldsSampleActivity.class);
    startActivity(intent);
  }

}