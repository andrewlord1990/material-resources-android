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

package com.github.andrewlord1990.materialandroidsample;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SampleActivity extends AppCompatActivity {

    private View primaryColorSquare;
    private View accentColorSquare;
    private Button colorSample;

    @ColorRes
    private static int defaultPrimaryColor = R.color.md_cyan_500;

    @ColorRes
    private static int defaultAccentColor = R.color.md_red_a200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sample);

        setupToolbar();
        setupFab();

        setupColorSample();
    }

    private void setupFab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupColorSample() {
        primaryColorSquare = findViewById(R.id.primary_color_square);
        if (primaryColorSquare != null) {
            primaryColorSquare.setBackgroundColor(ContextCompat.getColor(this, defaultPrimaryColor));
            primaryColorSquare.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        accentColorSquare = findViewById(R.id.accent_color_square);
        accentColorSquare.setBackgroundColor(ContextCompat.getColor(this, defaultAccentColor));
        accentColorSquare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        colorSample = (Button) findViewById(R.id.color_sample_button);
        if (colorSample != null) {
            colorSample.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onColorSampleSelected();
                }
            });
        }
    }

    private void onColorSampleSelected() {

    }

}
