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

package com.github.andrewlord1990.materialandroid.component.list;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

import com.github.andrewlord1990.materialandroid.R;
import com.github.andrewlord1990.materialandroid.component.list.ListItemView.ListItemVariant;

import org.assertj.android.api.widget.AbstractFrameLayoutAssert;
import org.assertj.core.api.Assertions;
import org.robolectric.internal.ShadowExtractor;
import org.robolectric.shadows.ShadowDrawable;

public abstract class AbstractListItemViewAssert<AssertT extends AbstractListItemViewAssert<AssertT, ViewT>,
    ViewT extends ListItemView> extends AbstractFrameLayoutAssert<AssertT, ViewT> {

  protected AbstractListItemViewAssert(ViewT actual, Class<AssertT> selfType) {
    super(actual, selfType);
  }

  private static ShadowDrawable shadowOf(Drawable actual) {
    return (ShadowDrawable) ShadowExtractor.extract(actual);
  }

  public AbstractListItemViewAssert hasVariant(@ListItemVariant int expectedVariant) {
    isNotNull();
    Assertions.assertThat(actual.getVariant())
        .overridingErrorMessage("Expected variant <%s> but was <%s>.",
            expectedVariant, actual.getVariant())
        .isEqualTo(expectedVariant);
    return myself;
  }

  public AbstractListItemViewAssert hasSecondaryTextView() {
    isNotNull();
    Assertions.assertThat(actual.findViewById(R.id.list_secondary_text))
        .overridingErrorMessage("Expected secondary text view to be present but it is not.")
        .isNotNull();
    return myself;
  }

  public AbstractListItemViewAssert doesNotHaveSecondaryTextView() {
    isNotNull();
    Assertions.assertThat(actual.findViewById(R.id.list_secondary_text))
        .overridingErrorMessage("Expected secondary text view not to be present but it is.")
        .isNull();
    return myself;
  }

  public AbstractListItemViewAssert hasTertiaryTextView() {
    isNotNull();
    Assertions.assertThat(actual.findViewById(R.id.list_tertiary_text))
        .overridingErrorMessage("Expected tertiary text view to be present but it is not.")
        .isNotNull();
    return myself;
  }

  public AbstractListItemViewAssert doesNotHaveTertiaryTextView() {
    isNotNull();
    Assertions.assertThat(actual.findViewById(R.id.list_tertiary_text))
        .overridingErrorMessage("Expected tertiary text view not to be present but it is.")
        .isNull();
    return myself;
  }

  public AbstractListItemViewAssert hasIconView() {
    isNotNull();
    Assertions.assertThat(actual.findViewById(R.id.list_icon))
        .overridingErrorMessage("Expected icon view to be present but it is not.")
        .isNotNull();
    return myself;
  }

  public AbstractListItemViewAssert doesNotHaveIconView() {
    isNotNull();
    Assertions.assertThat(actual.findViewById(R.id.list_icon))
        .overridingErrorMessage("Expected icon view not to be present but it is.")
        .isNull();
    return myself;
  }

  public AbstractListItemViewAssert hasAvatarView() {
    isNotNull();
    Assertions.assertThat(actual.findViewById(R.id.list_avatar))
        .overridingErrorMessage("Expected avatar view to be present but it is not.")
        .isNotNull();
    return myself;
  }

  public AbstractListItemViewAssert doesNotHaveAvatarView() {
    isNotNull();
    Assertions.assertThat(actual.findViewById(R.id.list_avatar))
        .overridingErrorMessage("Expected avatar view not to be present but it is.")
        .isNull();
    return myself;
  }

  public AbstractListItemViewAssert hasPrimaryText(String expectedText) {
    isNotNull();
    Assertions.assertThat(actual.getPrimaryText())
        .overridingErrorMessage("Expected primary text <%s> but was <%s>.",
            expectedText, actual.getPrimaryText())
        .isEqualTo(expectedText);
    return myself;
  }

  public AbstractListItemViewAssert hasSecondaryText(String expectedText) {
    isNotNull();
    Assertions.assertThat(actual.getSecondaryText())
        .overridingErrorMessage("Expected secondary text <%s> but was <%s>.",
            expectedText, actual.getSecondaryText())
        .isEqualTo(expectedText);
    return myself;
  }

  public AbstractListItemViewAssert hasTertiaryText(String expectedText) {
    isNotNull();
    Assertions.assertThat(actual.getTertiaryText())
        .overridingErrorMessage("Expected tertiary text <%s> but was <%s>.",
            expectedText, actual.getTertiaryText())
        .isEqualTo(expectedText);
    return myself;
  }

  public AbstractListItemViewAssert hasIcon(@DrawableRes int expectedIcon) {
    isNotNull();
    ShadowDrawable shadowIcon = shadowOf(actual.getIcon());
    int createdFromResId = shadowIcon.getCreatedFromResId();
    Assertions.assertThat(createdFromResId)
        .overridingErrorMessage("Expected icon res ID <%s> but was <%s>.",
            expectedIcon, createdFromResId)
        .isEqualTo(expectedIcon);
    return myself;
  }

  public AbstractListItemViewAssert hasAvatar(@DrawableRes int expectedAvatar) {
    isNotNull();
    ShadowDrawable shadowAvatar = shadowOf(actual.getAvatar());
    int createdFromResId = shadowAvatar.getCreatedFromResId();
    Assertions.assertThat(createdFromResId)
        .overridingErrorMessage("Expected avatar res ID <%s> but was <%s>.",
            expectedAvatar, createdFromResId)
        .isEqualTo(expectedAvatar);
    return myself;
  }

}
