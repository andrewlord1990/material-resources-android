package com.github.andrewlord1990.materialandroid.component.grid;

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

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.github.andrewlord1990.materialandroid.R;
import com.github.andrewlord1990.materialandroid.robolectric.LibraryRobolectricTestRunner;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;

import static com.github.andrewlord1990.materialandroid.component.grid.GridItemViewAssert.assertThat;

@RunWith(LibraryRobolectricTestRunner.class)
public class GridItemViewTest {

  private static final String PRIMARY = "somePrimaryText";
  private static final String SECONDARY = "someSecondaryText";

  private GridItemView gridItemView;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    gridItemView = new GridItemView(RuntimeEnvironment.application);
    gridItemView.setLayoutParams(params);
    gridItemView.layout(0, 0, 500, 1000);
  }

  @Test
  public void givenContext_whenCreated_thenDefaultVariant() {
    GridItemView view = new GridItemView(RuntimeEnvironment.application);

    assertThat(view).hasVariant(GridItemView.VARIANT_ONE_LINE_TEXT);
  }

  @Test
  public void givenAttrsWithNoCustomAttributes_whenCreated_thenSetupWithDefaults() {
    AttributeSet attrs = Robolectric.buildAttributeSet().build();

    GridItemView view = new GridItemView(RuntimeEnvironment.application, attrs);

    assertThat(view).hasVariant(GridItemView.VARIANT_ONE_LINE_TEXT);
  }

  @Test
  public void givenVariantInAttrs_whenCreated_thenHasVariant() {
    AttributeSet attrs = Robolectric.buildAttributeSet()
        .addAttribute(R.attr.ma_grid_item_variant, "two_line_text_icon")
        .build();

    GridItemView view = new GridItemView(RuntimeEnvironment.application, attrs);

    assertThat(view)
        .hasVariant(GridItemView.VARIANT_TWO_LINE_TEXT_ICON)
        .hasSecondaryTextView()
        .hasIconView();
  }

  @Test
  public void givenTextsInAttrs_whenCreated_thenHasTexts() {
    AttributeSet attrs = Robolectric.buildAttributeSet()
        .addAttribute(R.attr.ma_grid_item_variant, "two_line_text")
        .addAttribute(R.attr.ma_grid_text_primary, PRIMARY)
        .addAttribute(R.attr.ma_grid_text_secondary, SECONDARY)
        .build();

    GridItemView view = new GridItemView(RuntimeEnvironment.application, attrs, 0);

    assertThat(view)
        .hasPrimaryText(PRIMARY)
        .hasSecondaryText(SECONDARY)
        .hasSecondaryTextView();
  }

  @Test
  public void givenTextColorsInAttrs_whenCreated_thenHasTextColors() {
    AttributeSet attrs = Robolectric.buildAttributeSet()
        .addAttribute(R.attr.ma_grid_item_variant, "two_line_text")
        .addAttribute(R.attr.ma_grid_text_primary_color, "@color/ma_orange_50")
        .addAttribute(R.attr.ma_grid_text_secondary_color, "@color/ma_light_blue_a700")
        .build();

    GridItemView view = new GridItemView(RuntimeEnvironment.application, attrs, 0, 0);

    assertThat(view)
        .hasPrimaryTextColor(getColor(R.color.ma_orange_50))
        .hasSecondaryTextColor(getColor(R.color.ma_light_blue_a700));
  }

  @Test
  public void givenIconInAttrs_whenCreated_thenHasIcon() {
    AttributeSet attrs = Robolectric.buildAttributeSet()
        .addAttribute(R.attr.ma_grid_item_variant, "one_line_text_icon")
        .addAttribute(R.attr.ma_grid_icon, "@drawable/ic_icon_square")
        .build();

    GridItemView view = new GridItemView(RuntimeEnvironment.application, attrs, 0);

    assertThat(view)
        .hasIcon(R.drawable.ic_icon_square)
        .hasIconView();
  }

  @Test
  public void givenOneLineText_whenSetVariant_thenVariantChanged() {
    gridItemView.setVariant(GridItemView.VARIANT_ONE_LINE_TEXT);

    assertThat(gridItemView)
        .hasVariant(GridItemView.VARIANT_ONE_LINE_TEXT)
        .doesNotHaveSecondaryTextView()
        .doesNotHaveIconView();
  }

  @Test
  public void givenOneLineTextIcon_whenSetVariant_thenVariantChanged() {
    gridItemView.setVariant(GridItemView.VARIANT_ONE_LINE_TEXT_ICON);

    assertThat(gridItemView)
        .hasVariant(GridItemView.VARIANT_ONE_LINE_TEXT_ICON)
        .hasIconView()
        .doesNotHaveSecondaryTextView();
  }

  @Test
  public void givenTwoLineText_whenSetVariant_thenVariantChanged() {
    gridItemView.setVariant(GridItemView.VARIANT_TWO_LINE_TEXT);

    assertThat(gridItemView)
        .hasVariant(GridItemView.VARIANT_TWO_LINE_TEXT)
        .hasSecondaryTextView()
        .doesNotHaveIconView();
  }

  @Test
  public void givenTwoLineTextIcon_whenSetVariant_thenVariantChanged() {
    gridItemView.setVariant(GridItemView.VARIANT_TWO_LINE_TEXT_ICON);

    assertThat(gridItemView)
        .hasVariant(GridItemView.VARIANT_TWO_LINE_TEXT_ICON)
        .hasSecondaryTextView()
        .hasIconView();
  }

  @Test
  @SuppressWarnings("WrongConstant")
  public void givenInvalidVariant_whenSetVariant_thenHasDefaultVariant() {
    gridItemView.setVariant(10000);

    assertThat(gridItemView)
        .doesNotHaveSecondaryTextView()
        .doesNotHaveIconView();
  }

  @Test
  public void givenTexts_whenSetVariant_thenTextSetOnNewViews() {
    gridItemView.setVariant(GridItemView.VARIANT_ONE_LINE_TEXT);
    gridItemView.setPrimaryText(PRIMARY);
    gridItemView.setSecondaryText(SECONDARY);

    gridItemView.setVariant(GridItemView.VARIANT_TWO_LINE_TEXT);

    assertThat(gridItemView)
        .hasPrimaryText(PRIMARY)
        .hasSecondaryText(SECONDARY);
  }

  @Test
  public void givenTextColors_whenSetVariant_thenTextColorsSetOnNewViews() {
    gridItemView.setVariant(GridItemView.VARIANT_ONE_LINE_TEXT);
    int expectedPrimaryColor = getColor(R.color.ma_amber_100);
    gridItemView.setPrimaryTextColor(expectedPrimaryColor);
    int expectedSecondaryColor = getColor(R.color.ma_blue_100);
    gridItemView.setSecondaryTextColor(expectedSecondaryColor);

    gridItemView.setVariant(GridItemView.VARIANT_TWO_LINE_TEXT);

    assertThat(gridItemView)
        .hasPrimaryTextColor(expectedPrimaryColor)
        .hasSecondaryTextColor(expectedSecondaryColor);
  }

  @Test
  public void givenTextColorStateLists_whenSetVariant_thenTextColorStateListsSetOnNewViews() {
    gridItemView.setVariant(GridItemView.VARIANT_ONE_LINE_TEXT);
    ColorStateList expectedPrimaryColor = createColorStateList(Color.RED, Color.CYAN);
    gridItemView.setPrimaryTextColor(expectedPrimaryColor);
    ColorStateList expectedSecondaryColor = createColorStateList(Color.BLACK, Color.GREEN);
    gridItemView.setSecondaryTextColor(expectedSecondaryColor);

    gridItemView.setVariant(GridItemView.VARIANT_TWO_LINE_TEXT);

    assertThat(gridItemView)
        .hasPrimaryTextColor(expectedPrimaryColor)
        .hasSecondaryTextColor(expectedSecondaryColor);
  }

  @Test
  public void givenStringResource_whenSetPrimaryText_thenHasPrimaryText() {
    gridItemView.setPrimaryText(R.string.list_item_avatar_cd);

    assertThat(gridItemView)
        .hasPrimaryText("Avatar");
  }

  @Test
  public void whenSetPrimaryText_thenHasPrimaryText() {
    final String expectedText = "someText";

    gridItemView.setPrimaryText(expectedText);

    assertThat(gridItemView)
        .hasPrimaryText(expectedText);
  }

  @Test
  public void givenStateList_whenSetPrimaryTextColor_thenHasPrimaryTextColor() {
    final ColorStateList expectedColor = createColorStateList(Color.DKGRAY, Color.RED);

    gridItemView.setPrimaryTextColor(expectedColor);

    assertThat(gridItemView)
        .hasPrimaryTextColor(expectedColor);
  }

  @Test
  public void givenColorValue_whenSetPrimaryTextColor_thenHasPrimaryTextColor() {
    final int expectedColor = Color.BLUE;

    gridItemView.setPrimaryTextColor(expectedColor);

    assertThat(gridItemView)
        .hasPrimaryTextColor(expectedColor);
  }

  @Test
  public void whenSetPrimaryTextColorRes_thenHasPrimaryTextColor() {
    @ColorRes final int expectedColor = R.color.ma_red_400;

    gridItemView.setPrimaryTextColorRes(expectedColor);

    assertThat(gridItemView)
        .hasPrimaryTextColor(getColor(expectedColor));
  }

  @Test
  public void givenNoSecondaryTextView_whenGetSecondaryText_thenNull() {
    gridItemView.setVariant(GridItemView.VARIANT_ONE_LINE_TEXT);

    CharSequence actual = gridItemView.getSecondaryText();

    Assertions.assertThat(actual).isNull();
  }

  @Test
  public void givenStringResource_whenSetSecondaryText_thenHasSecondaryText() {
    gridItemView.setSecondaryText(R.string.list_item_icon_cd);
    gridItemView.setVariant(GridItemView.VARIANT_TWO_LINE_TEXT);

    assertThat(gridItemView)
        .hasSecondaryText("Icon");
  }

  @Test
  public void whenSetSecondaryText_thenHasSecondaryText() {
    final String expectedText = "someText";

    gridItemView.setSecondaryText(expectedText);
    gridItemView.setVariant(GridItemView.VARIANT_TWO_LINE_TEXT);

    assertThat(gridItemView)
        .hasSecondaryText(expectedText);
  }

  @Test
  public void givenNoSecondaryTextView_whenGetSecondaryTextColors_thenNull() {
    gridItemView.setVariant(GridItemView.VARIANT_ONE_LINE_TEXT);

    ColorStateList actual = gridItemView.getSecondaryTextColors();

    Assertions.assertThat(actual).isNull();
  }

  @Test
  public void givenNoSecondaryTextView_whenGetCurrentSecondaryTextColor_thenZero() {
    gridItemView.setVariant(GridItemView.VARIANT_ONE_LINE_TEXT);

    int actual = gridItemView.getCurrentSecondaryTextColor();

    Assertions.assertThat(actual).isEqualTo(0);
  }

  @Test
  public void givenStateList_whenSetSecondaryTextColor_thenHasSecondarTextColor() {
    final ColorStateList expectedColor = createColorStateList(Color.DKGRAY, Color.RED);

    gridItemView.setSecondaryTextColor(expectedColor);
    gridItemView.setVariant(GridItemView.VARIANT_TWO_LINE_TEXT);

    assertThat(gridItemView)
        .hasSecondaryTextColor(expectedColor);
  }

  @Test
  public void givenColorValue_whenSetSecondaryTextColor_thenHasSecondaryTextColor() {
    final int expectedColor = Color.BLUE;

    gridItemView.setSecondaryTextColor(expectedColor);
    gridItemView.setVariant(GridItemView.VARIANT_TWO_LINE_TEXT);

    assertThat(gridItemView)
        .hasSecondaryTextColor(expectedColor);
  }

  @Test
  public void whenSetSecondaryTextColorRes_thenHasSecondaryTextColor() {
    @ColorRes final int expectedColor = R.color.ma_red_400;

    gridItemView.setSecondaryTextColorRes(expectedColor);
    gridItemView.setVariant(GridItemView.VARIANT_TWO_LINE_TEXT);

    assertThat(gridItemView)
        .hasSecondaryTextColor(getColor(expectedColor));
  }

  @Test
  public void givenNoIconView_whenGetIcon_thenNull() {
    gridItemView.setVariant(GridItemView.VARIANT_TWO_LINE_TEXT);

    Drawable actual = gridItemView.getIcon();

    Assertions.assertThat(actual).isNull();
  }

  @Test
  public void givenDrawableResource_whenSetIcon_thenHasIcon() {
    gridItemView.setIcon(R.drawable.ic_icon_square);
    gridItemView.setVariant(GridItemView.VARIANT_ONE_LINE_TEXT_ICON);

    assertThat(gridItemView).hasIcon(R.drawable.ic_icon_square);
  }

  @Test
  public void givenOneLine_whenSetIconGravity_thenHasIconGravity() {
    gridItemView.setVariant(GridItemView.VARIANT_ONE_LINE_TEXT_ICON);

    gridItemView.setIconGravity(GridItemView.ICON_GRAVITY_END);

    assertThat(gridItemView).hasIconGravity(GridItemView.ICON_GRAVITY_END);
  }

  @Test
  public void givenTwoLine_whenSetIconGravity_thenHasIconGravity() {
    gridItemView.setVariant(GridItemView.VARIANT_TWO_LINE_TEXT_ICON);

    gridItemView.setIconGravity(GridItemView.ICON_GRAVITY_END);

    assertThat(gridItemView).hasIconGravity(GridItemView.ICON_GRAVITY_END);
  }

  @ColorInt
  private int getColor(@ColorRes int color) {
    return ContextCompat.getColor(RuntimeEnvironment.application, color);
  }

  private ColorStateList createColorStateList(@ColorInt int color0, @ColorInt int color1) {
    int[] state0 = new int[] {0, 1};
    int[] state1 = new int[] {2, 3};
    int[][] states = new int[][] {state0, state1};
    int[] colors = new int[] {color0, color1};
    return new ColorStateList(states, colors);
  }

}