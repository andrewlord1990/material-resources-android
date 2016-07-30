/*
 *  Copyright (C) 2016 Andrew Lord
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package com.github.andrewlord1990.materialandroid.component.list;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;

import com.github.andrewlord1990.materialandroid.R;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.internal.ShadowExtractor;
import org.robolectric.res.Attribute;
import org.robolectric.res.ResourceLoader;
import org.robolectric.shadows.RoboAttributeSet;
import org.robolectric.shadows.ShadowResources;

import java.util.ArrayList;
import java.util.List;

import static com.github.andrewlord1990.materialandroid.component.list.ListItemViewAssert.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
public class ListItemViewTest {

  private static final String PACKAGE = "com.github.andrewlord1990.materialandroid";
  private static final String PRIMARY = "somePrimaryText";
  private static final String SECONDARY = "someSecondaryText";
  private static final String TERTIARY = "someTertiaryText";

  private ListItemView listItemView;

  private static ShadowResources shadowOf(Resources actual) {
    return (ShadowResources) ShadowExtractor.extract(actual);
  }

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    LayoutParams params = new LayoutParams(
        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

    listItemView = new ListItemView(RuntimeEnvironment.application);
    listItemView.setLayoutParams(params);
    listItemView.layout(0, 0, 500, 1000);
  }

  @Test
  public void givenContext_whenCreated_thenDefaultVariant() {
    //When
    ListItemView view = new ListItemView(RuntimeEnvironment.application);

    //Then
    assertThat(view).hasVariant(ListItemView.VARIANT_ONE_LINE_TEXT);
  }

  @Test
  public void givenAttrsWithNoCustomAttributes_whenCreated_thenSetupWithDefaults() {
    //Given
    AttributeSet attrs = createAttributeSet(new ArrayList<Attribute>());

    //When
    ListItemView view = new ListItemView(RuntimeEnvironment.application, attrs);

    //Then
    assertThat(view).hasVariant(ListItemView.VARIANT_ONE_LINE_TEXT);
  }

  @Test
  public void givenVariantInAttrs_whenCreated_thenHasVariant() {
    //Given
    List<Attribute> attributes = new ArrayList<>();
    attributes.add(createAttribute("md_list_item_variant", "two_line_text_icon"));
    AttributeSet attrs = createAttributeSet(attributes);

    //When
    ListItemView view = new ListItemView(RuntimeEnvironment.application, attrs);

    //Then
    assertThat(view)
        .hasVariant(ListItemView.VARIANT_TWO_LINE_TEXT_ICON)
        .hasSecondaryTextView()
        .hasIconView();
  }

  @Test
  public void givenTextsInAttrs_whenCreated_thenHasTexts() {
    //Given
    List<Attribute> attributes = new ArrayList<>();
    attributes.add(createAttribute("md_list_item_variant", "three_line_text"));
    attributes.add(createAttribute("md_list_text_primary", PRIMARY));
    attributes.add(createAttribute("md_list_text_secondary", SECONDARY));
    attributes.add(createAttribute("md_list_text_tertiary", TERTIARY));
    AttributeSet attrs = createAttributeSet(attributes);

    //When
    ListItemView view = new ListItemView(RuntimeEnvironment.application, attrs);

    //Then
    assertThat(view)
        .hasPrimaryText(PRIMARY)
        .hasSecondaryText(SECONDARY)
        .hasTertiaryText(TERTIARY)
        .hasSecondaryTextView()
        .hasTertiaryTextView();
  }

  @Test
  public void givenIconInAttrs_whenCreated_thenHasIcon() {
    //Given
    List<Attribute> attributes = new ArrayList<>();
    attributes.add(createAttribute("md_list_item_variant", "one_line_text_icon"));
    attributes.add(createAttribute("md_list_icon", "@drawable/ic_icon_square"));
    AttributeSet attrs = createAttributeSet(attributes);

    //When
    ListItemView view = new ListItemView(RuntimeEnvironment.application, attrs, 0);

    //Then
    assertThat(view)
        .hasIcon(R.drawable.ic_icon_square)
        .hasIconView();
  }

  @Test
  public void givenAvatarInAttrs_whenCreated_thenHasAvatar() {
    //Given
    List<Attribute> attributes = new ArrayList<>();
    attributes.add(createAttribute("md_list_item_variant", "one_line_text_avatar"));
    attributes.add(createAttribute("md_list_avatar", "@drawable/ic_avatar_circle"));
    AttributeSet attrs = createAttributeSet(attributes);

    //When
    ListItemView view = new ListItemView(RuntimeEnvironment.application, attrs, 0, 0);

    //Then
    assertThat(view)
        .hasAvatar(R.drawable.ic_avatar_circle)
        .hasAvatarView();
  }

  @Test
  public void givenOneLineText_whenSetVariant_thenVariantChanged() {
    //When
    listItemView.setVariant(ListItemView.VARIANT_ONE_LINE_TEXT);

    //Then
    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_ONE_LINE_TEXT)
        .doesNotHaveSecondaryTextView()
        .doesNotHaveTertiaryTextView()
        .doesNotHaveIconView()
        .doesNotHaveAvatarView();
  }

  @Test
  public void givenOneLineTextIcon_whenSetVariant_thenVariantChanged() {
    //When
    listItemView.setVariant(ListItemView.VARIANT_ONE_LINE_TEXT_ICON);

    //Then
    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_ONE_LINE_TEXT_ICON)
        .hasIconView()
        .doesNotHaveSecondaryTextView()
        .doesNotHaveTertiaryTextView()
        .doesNotHaveAvatarView();
  }

  @Test
  public void givenOneLineTextAvatar_whenSetVariant_thenVariantChanged() {
    //When
    listItemView.setVariant(ListItemView.VARIANT_ONE_LINE_TEXT_AVATAR);

    //Then
    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_ONE_LINE_TEXT_AVATAR)
        .hasAvatarView()
        .doesNotHaveSecondaryTextView()
        .doesNotHaveTertiaryTextView()
        .doesNotHaveIconView();
  }

  @Test
  public void givenOneLineTextIconAndAvatar_whenSetVariant_thenVariantChanged() {
    //When
    listItemView.setVariant(ListItemView.VARIANT_ONE_LINE_TEXT_ICON_AVATAR);

    //Then
    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_ONE_LINE_TEXT_ICON_AVATAR)
        .hasIconView()
        .hasAvatarView()
        .doesNotHaveSecondaryTextView()
        .doesNotHaveTertiaryTextView();
  }

  @Test
  public void givenTwoLineText_whenSetVariant_thenVariantChanged() {
    //When
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT);

    //Then
    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_TWO_LINE_TEXT)
        .hasSecondaryTextView()
        .doesNotHaveTertiaryTextView()
        .doesNotHaveIconView()
        .doesNotHaveAvatarView();
  }

  @Test
  public void givenTwoLineTextIcon_whenSetVariant_thenVariantChanged() {
    //When
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT_ICON);

    //Then
    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_TWO_LINE_TEXT_ICON)
        .hasSecondaryTextView()
        .hasIconView()
        .doesNotHaveTertiaryTextView()
        .doesNotHaveAvatarView();
  }

  @Test
  public void givenTwoLineTextAvatar_whenSetVariant_thenVariantChanged() {
    //When
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT_AVATAR);

    //Then
    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_TWO_LINE_TEXT_AVATAR)
        .hasSecondaryTextView()
        .hasAvatarView()
        .doesNotHaveTertiaryTextView()
        .doesNotHaveIconView();
  }

  @Test
  public void givenTwoLineTextIconAndAvatar_whenSetVariant_thenVariantChanged() {
    //When
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT_ICON_AVATAR);

    //Then
    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_TWO_LINE_TEXT_ICON_AVATAR)
        .hasSecondaryTextView()
        .hasIconView()
        .hasAvatarView()
        .doesNotHaveTertiaryTextView();
  }

  @Test
  public void givenThreeLineText_whenSetVariant_thenVariantChanged() {
    //When
    listItemView.setVariant(ListItemView.VARIANT_THREE_LINE_TEXT);

    //Then
    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_THREE_LINE_TEXT)
        .hasSecondaryTextView()
        .hasTertiaryTextView()
        .doesNotHaveIconView()
        .doesNotHaveAvatarView();
  }

  @Test
  public void givenThreeLineTextIcon_whenSetVariant_thenVariantChanged() {
    //When
    listItemView.setVariant(ListItemView.VARIANT_THREE_LINE_TEXT_ICON);

    //Then
    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_THREE_LINE_TEXT_ICON)
        .hasSecondaryTextView()
        .hasTertiaryTextView()
        .hasIconView()
        .doesNotHaveAvatarView();
  }

  @Test
  public void givenThreeLineTextAvatar_whenSetVariant_thenVariantChanged() {
    //When
    listItemView.setVariant(ListItemView.VARIANT_THREE_LINE_TEXT_AVATAR);

    //Then
    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_THREE_LINE_TEXT_AVATAR)
        .hasSecondaryTextView()
        .hasTertiaryTextView()
        .hasAvatarView()
        .doesNotHaveIconView();
  }

  @Test
  public void givenThreeLineTextIconAndAvatar_whenSetVariant_thenVariantChanged() {
    //When
    listItemView.setVariant(ListItemView.VARIANT_THREE_LINE_TEXT_ICON_AVATAR);

    //Then
    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_THREE_LINE_TEXT_ICON_AVATAR)
        .hasSecondaryTextView()
        .hasTertiaryTextView()
        .hasIconView()
        .hasAvatarView();
  }

  @Test
  public void givenInvalidVariant_whenSetVariant_thenHasDefaultVariant() {
    //When
    listItemView.setVariant(10000);

    //Then
    assertThat(listItemView)
        .doesNotHaveSecondaryTextView()
        .doesNotHaveTertiaryTextView()
        .doesNotHaveIconView()
        .doesNotHaveAvatarView();
  }

  @Test
  public void givenStringResource_whenSetPrimaryText_thenHasPrimaryText() {
    //When
    listItemView.setPrimaryText(R.string.list_item_avatar_cd);

    //Then
    assertThat(listItemView)
        .hasPrimaryText("Avatar");
  }

  @Test
  public void givenNoSecondaryTextView_whenGetSecondaryText_thenNull() {
    //Given
    listItemView.setVariant(ListItemView.VARIANT_ONE_LINE_TEXT);

    //When
    CharSequence actual = listItemView.getSecondaryText();

    //Then
    Assertions.assertThat(actual).isNull();
  }

  @Test
  public void givenStringResource_whenSetSecondaryText_thenHasSecondaryText() {
    //When
    listItemView.setSecondaryText(R.string.list_item_icon_cd);
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT);

    //Then
    assertThat(listItemView)
        .hasSecondaryText("Icon");
  }

  @Test
  public void givenNoTertiaryTextView_whenGetTertiaryText_thenNull() {
    //Given
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT);

    //When
    CharSequence actual = listItemView.getTertiaryText();

    //Then
    Assertions.assertThat(actual).isNull();
  }

  @Test
  public void givenStringResource_whenSetTertiaryText_thenHasTertiaryText() {
    //When
    listItemView.setTertiaryText(R.string.list_item_avatar_cd);
    listItemView.setVariant(ListItemView.VARIANT_THREE_LINE_TEXT);

    //Then
    assertThat(listItemView)
        .hasTertiaryText("Avatar");
  }

  @Test
  public void givenNoIconView_whenGetIcon_thenNull() {
    //Given
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT);

    //When
    Drawable actual = listItemView.getIcon();

    //Then
    Assertions.assertThat(actual).isNull();
  }

  @Test
  public void givenDrawableResource_whenSetIcon_thenHasIcon() {
    //When
    listItemView.setIcon(R.drawable.ic_icon_square);
    listItemView.setVariant(ListItemView.VARIANT_ONE_LINE_TEXT_ICON);

    //Then
    assertThat(listItemView).hasIcon(R.drawable.ic_icon_square);
  }

  @Test
  public void givenNoAvatarView_whenGetAvatar_thenNull() {
    //Given
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT);

    //When
    Drawable actual = listItemView.getAvatar();

    //Then
    Assertions.assertThat(actual).isNull();
  }

  @Test
  public void givenDrawableResource_whenSetAvatar_thenHasAvatar() {
    //When
    listItemView.setAvatar(R.drawable.ic_avatar_circle);
    listItemView.setVariant(ListItemView.VARIANT_ONE_LINE_TEXT_AVATAR);

    //Then
    assertThat(listItemView).hasAvatar(R.drawable.ic_avatar_circle);
  }

  private Attribute createAttribute(String name, String value) {
    return new Attribute(PACKAGE + ":attr/" + name, value, PACKAGE);
  }

  private AttributeSet createAttributeSet(List<Attribute> attributes) {
    Resources resources = RuntimeEnvironment.application.getResources();
    ResourceLoader resourceLoader = shadowOf(resources).getResourceLoader();
    return new RoboAttributeSet(attributes, resourceLoader);
  }

}