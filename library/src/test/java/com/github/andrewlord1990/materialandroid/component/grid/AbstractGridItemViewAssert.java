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

package com.github.andrewlord1990.materialandroid.component.grid;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

import com.github.andrewlord1990.materialandroid.R;
import com.github.andrewlord1990.materialandroid.component.grid.GridItemView.GridItemVariant;
import com.github.andrewlord1990.materialandroid.component.grid.GridItemView.IconGravity;

import org.assertj.android.api.widget.AbstractFrameLayoutAssert;
import org.assertj.core.api.Assertions;
import org.robolectric.internal.ShadowExtractor;
import org.robolectric.shadows.ShadowDrawable;

public abstract class AbstractGridItemViewAssert<AssertT extends AbstractGridItemViewAssert<AssertT, ViewT>,
    ViewT extends GridItemView> extends AbstractFrameLayoutAssert<AssertT, ViewT> {

  protected AbstractGridItemViewAssert(ViewT actual, Class<AssertT> selfType) {
    super(actual, selfType);
  }

  private static ShadowDrawable shadowOf(Drawable actual) {
    return (ShadowDrawable) ShadowExtractor.extract(actual);
  }

  public AbstractGridItemViewAssert hasVariant(@GridItemVariant int expectedVariant) {
    isNotNull();
    Assertions.assertThat(actual.getVariant())
        .overridingErrorMessage("Expected variant <%s> but was <%s>.",
            expectedVariant, actual.getVariant())
        .isEqualTo(expectedVariant);
    return myself;
  }

  public AbstractGridItemViewAssert hasSecondaryTextView() {
    isNotNull();
    Assertions.assertThat(actual.findViewById(R.id.grid_list_label_line_2))
        .overridingErrorMessage("Expected secondary text view to be present but it is not.")
        .isNotNull();
    return myself;
  }

  public AbstractGridItemViewAssert doesNotHaveSecondaryTextView() {
    isNotNull();
    Assertions.assertThat(actual.findViewById(R.id.grid_list_label_line_2))
        .overridingErrorMessage("Expected secondary text view not to be present but it is.")
        .isNull();
    return myself;
  }

  public AbstractGridItemViewAssert hasIconView() {
    isNotNull();
    Assertions.assertThat(actual.findViewById(R.id.grid_list_label_icon))
        .overridingErrorMessage("Expected icon view to be present but it is not.")
        .isNotNull();
    return myself;
  }

  public AbstractGridItemViewAssert doesNotHaveIconView() {
    isNotNull();
    Assertions.assertThat(actual.findViewById(R.id.grid_list_label_icon))
        .overridingErrorMessage("Expected icon view not to be present but it is.")
        .isNull();
    return myself;
  }

  public AbstractGridItemViewAssert hasPrimaryText(String expectedText) {
    isNotNull();
    Assertions.assertThat(actual.getPrimaryText())
        .overridingErrorMessage("Expected primary text <%s> but was <%s>.",
            expectedText, actual.getPrimaryText())
        .isEqualTo(expectedText);
    return myself;
  }

  public AbstractGridItemViewAssert hasPrimaryTextColor(@ColorInt int expectedColor) {
    isNotNull();
    Assertions.assertThat(actual.getCurrentPrimaryTextColor())
        .overridingErrorMessage("Expected primary text color <%s> but was <%s>.",
            expectedColor, actual.getCurrentPrimaryTextColor())
        .isEqualTo(expectedColor);
    return myself;
  }

  public AbstractGridItemViewAssert hasPrimaryTextColor(ColorStateList expectedColor) {
    isNotNull();
    Assertions.assertThat(actual.getPrimaryTextColors())
        .overridingErrorMessage("Expected primary text color <%s> but was <%s>.",
            expectedColor, actual.getPrimaryTextColors())
        .isEqualTo(expectedColor);
    return myself;
  }

  public AbstractGridItemViewAssert hasSecondaryText(String expectedText) {
    isNotNull();
    Assertions.assertThat(actual.getSecondaryText())
        .overridingErrorMessage("Expected secondary text <%s> but was <%s>.",
            expectedText, actual.getSecondaryText())
        .isEqualTo(expectedText);
    return myself;
  }

  public AbstractGridItemViewAssert hasSecondaryTextColor(@ColorInt int expectedColor) {
    isNotNull();
    Assertions.assertThat(actual.getCurrentSecondaryTextColor())
        .overridingErrorMessage("Expected secondary text color <%s> but was <%s>.",
            expectedColor, actual.getCurrentSecondaryTextColor())
        .isEqualTo(expectedColor);
    return myself;
  }

  public AbstractGridItemViewAssert hasSecondaryTextColor(ColorStateList expectedColor) {
    isNotNull();
    Assertions.assertThat(actual.getSecondaryTextColors())
        .overridingErrorMessage("Expected secondary text color <%s> but was <%s>.",
            expectedColor, actual.getSecondaryTextColors())
        .isEqualTo(expectedColor);
    return myself;
  }

  public AbstractGridItemViewAssert hasIcon(@DrawableRes int expectedIcon) {
    isNotNull();
    ShadowDrawable shadowIcon = shadowOf(actual.getIcon());
    int createdFromResId = shadowIcon.getCreatedFromResId();
    Assertions.assertThat(createdFromResId)
        .overridingErrorMessage("Expected icon res ID <%s> but was <%s>.",
            expectedIcon, createdFromResId)
        .isEqualTo(expectedIcon);
    return myself;
  }

  public AbstractGridItemViewAssert hasIconGravity(@IconGravity int expectedGravity) {
    isNotNull();
    Assertions.assertThat(actual.getIconGravity())
        .overridingErrorMessage("Expected icon gravity <%s> but was <%s>.",
            expectedGravity, actual.getIconGravity())
        .isEqualTo(expectedGravity);
    return myself;
  }

}
