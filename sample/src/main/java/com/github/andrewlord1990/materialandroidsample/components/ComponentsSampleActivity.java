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
import com.github.andrewlord1990.materialandroidsample.components.lists.ListsSampleActivity;

public class ComponentsSampleActivity extends BaseSampleActivity {

    private static int lastListType = 0;

    private Spinner listsSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_components_sample);

        setupToolbar();
        setupListsButton();
    }

    @Override
    protected void onResume() {
        super.onResume();

        listsSpinner.setSelection(lastListType);
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
                }
            });
        }
        Button listsButton = (Button) findViewById(R.id.lists);
        if (listsButton != null) {
            listsButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
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

}