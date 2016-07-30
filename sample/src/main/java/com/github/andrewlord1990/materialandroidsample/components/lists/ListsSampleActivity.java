package com.github.andrewlord1990.materialandroidsample.components.lists;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.andrewlord1990.materialandroidsample.BaseSampleActivity;
import com.github.andrewlord1990.materialandroidsample.R;

public class ListsSampleActivity extends BaseSampleActivity {

  public static final String EXTRA_LIST_TYPE = "extraListType";

  private int listType;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    listType = getIntent().getIntExtra(EXTRA_LIST_TYPE, 0);

    setContentView(R.layout.activity_lists_sample);

    setupToolbar();
    setupList();
  }

  private void setupToolbar() {
    setTitle(R.string.sample_lists_title);
    showUpButton();
  }

  private void setupList() {
    RecyclerView list = (RecyclerView) findViewById(R.id.recyclerView);
    if (list != null) {
      list.setLayoutManager(new LinearLayoutManager(this));
      list.setHasFixedSize(true);
      list.setAdapter(new ListAdapter(this, listType));
    }
  }

}
