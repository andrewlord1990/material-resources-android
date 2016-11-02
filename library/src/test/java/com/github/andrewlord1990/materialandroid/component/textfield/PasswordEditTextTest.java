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

package com.github.andrewlord1990.materialandroid.component.textfield;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.github.andrewlord1990.materialandroid.R;
import com.github.andrewlord1990.materialandroid.robolectric.LibraryRobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static com.github.andrewlord1990.materialandroid.component.textfield.PasswordEditTextAssert.assertThat;

@RunWith(LibraryRobolectricTestRunner.class)
public class PasswordEditTextTest {

  private PasswordEditText passwordView;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    LayoutParams params = new LayoutParams(
        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

    passwordView = new PasswordEditText(RuntimeEnvironment.application);
    passwordView.setLayoutParams(params);
    passwordView.layout(0, 0, 500, 1000);
  }

  @Test
  public void givenContext_whenCreated_thenSetupWithDefaults() {
    PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application);

    assertThat(actual)
        .hasHiddenPassword()
        .hasDefaultToggle()
        .hasToggleHiddenAlpha();
  }

  @Test
  public void givenAttrsWithNoCustomAttributes_whenCreated_thenSetupWithDefaults() {
    AttributeSet attrs = Robolectric.buildAttributeSet().build();

    PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application, attrs);

    assertThat(actual)
        .hasDefaultToggle()
        .hasToggleHiddenAlpha()
        .hasHiddenPassword();
  }

  @Test
  public void givenCustomIconsInAttrs_whenCreated_thenHasCustomHiddenIcon() {
    AttributeSet attrs = createCustomIconsAttributeSet();

    PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application, attrs);

    assertThat(actual).hasToggle(R.drawable.ic_avatar_circle);
  }

  @Test
  public void givenCustomIconsInAttrs_whenCreatedAndVisibilityToggled_thenHasCustomShownIcon() {
    AttributeSet attrs = createCustomIconsAttributeSet();

    PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application, attrs, 0);
    actual.togglePasswordVisibility();

    assertThat(actual).hasToggle(R.drawable.ic_icon_square);
  }

  @Test
  public void givenStrikeThroughTypeInAttrs_whenCreated_thenHasStrikeThroughToggle() {
    AttributeSet attrs = createStrikeThroughAttributeSet();

    PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application, attrs);

    assertThat(actual).hasStrikethroughToggle();
  }

  @Test
  public void givenStrikeThroughTypeInAttrs_whenCreatedAndVisibilityToggled_thenHasDefaultToggle() {
    AttributeSet attrs = createStrikeThroughAttributeSet();

    PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application, attrs, 0);
    actual.togglePasswordVisibility();

    assertThat(actual)
        .hasDefaultToggle()
        .hasToggleShownAlpha();
  }

  @Test
  public void givenPasswordShownInAttrs_whenCreated_thenPasswordShown() {
    AttributeSet attrs = Robolectric.buildAttributeSet()
        .addAttribute(R.attr.ma_password_shown, "true")
        .build();

    PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application, attrs);

    assertThat(actual)
        .hasVisiblePassword()
        .hasToggleShownAlpha();
  }

  private AttributeSet createStrikeThroughAttributeSet() {
    return Robolectric.buildAttributeSet()
        .addAttribute(R.attr.ma_password_toggle_type, "strikethrough")
        .build();
  }

  private AttributeSet createCustomIconsAttributeSet() {
    return Robolectric.buildAttributeSet()
        .addAttribute(R.attr.ma_password_hidden_drawable, "@drawable/ic_avatar_circle")
        .addAttribute(R.attr.ma_password_shown_drawable, "@drawable/ic_icon_square")
        .build();
  }

  @Test
  public void whenSetInputType_thenTypefaceUnchanged() {
    passwordView.setTypeface(Typeface.DEFAULT_BOLD);

    passwordView.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

    assertThat(passwordView)
        .hasInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
        .hasTypeface(Typeface.DEFAULT_BOLD);
  }

  @Test
  public void whenSetInputType_thenSelectionUnchanged() {
    int start = 10;
    int end = 12;
    String text = "a really long string with lots of content";
    passwordView.setText(text);
    passwordView.setSelection(start, end);

    passwordView.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

    assertThat(passwordView)
        .hasInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
        .hasSelectionStart(start)
        .hasSelectionEnd(end);
  }

  @Test
  public void givenLeftToRightTouchWithinToggle_whenOnTouchEvent_thenPasswordVisibilityToggled() {
    passwordView.setPasswordVisible(false);
    float xPosition = passwordView.getRight() - 10;
    float yPosition = passwordView.getTop() + 10;

    fireActionUpTouchEvent(passwordView, xPosition, yPosition);

    assertThat(passwordView)
        .hasVisiblePassword();
  }

  @Test
  @Config(sdk = 16)
  public void givenLessThanSdk17TouchWithinToggle_whenOnTouchEvent_thenPasswordVisibilityToggled() {
    ViewCompat.setLayoutDirection(passwordView, ViewCompat.LAYOUT_DIRECTION_RTL);
    passwordView.setPasswordVisible(false);
    float xPosition = passwordView.getRight() - 10;
    float yPosition = passwordView.getTop() + 10;

    fireActionUpTouchEvent(passwordView, xPosition, yPosition);

    assertThat(passwordView)
        .hasVisiblePassword();
  }

  @Test
  public void givenTouchOutsideToggle_whenOnTouchEvent_thenPasswordVisibilityNotToggled() {
    passwordView.setPasswordVisible(false);
    float xPosition = passwordView.getLeft() + 10;
    float yPosition = passwordView.getTop() + 10;

    fireActionUpTouchEvent(passwordView, xPosition, yPosition);

    assertThat(passwordView)
        .hasHiddenPassword();
  }

  @Test
  public void givenTouchActionDown_whenOnTouchEvent_thenPasswordVisibilityNotToggled() {
    passwordView.setPasswordVisible(false);
    float xPosition = passwordView.getLeft() + 10;
    float yPosition = passwordView.getTop() + 10;

    fireActionDownTouchEvent(passwordView, xPosition, yPosition);

    assertThat(passwordView)
        .hasHiddenPassword();
  }

  @Test
  public void givenPasswordCurrentlyVisible_whenTogglePasswordVisibility_thenPasswordHidden() {
    passwordView.setPasswordVisible(true);

    passwordView.togglePasswordVisibility();

    assertThat(passwordView)
        .hasHiddenPassword()
        .hasToggleHiddenAlpha()
        .hasDefaultToggle();
  }

  @Test
  public void givenPasswordCurrentlyHidden_whenTogglePasswordVisibility_thenPasswordShown() {
    passwordView.setPasswordVisible(false);

    passwordView.togglePasswordVisibility();

    assertThat(passwordView)
        .hasVisiblePassword()
        .hasToggleShownAlpha()
        .hasDefaultToggle();
  }

  @Test
  public void givenBeingSetToVisible_whenSetPasswordVisible_thenPasswordShownWithShownToggle() {
    passwordView.setPasswordVisible(true);

    assertThat(passwordView)
        .hasVisiblePassword()
        .hasToggleShownAlpha()
        .hasDefaultToggle();
  }

  @Test
  public void givenBeingSetToHidden_whenSetPasswordVisible_thenPasswordHiddenWithHiddenToggle() {
    passwordView.setPasswordVisible(true);

    passwordView.setPasswordVisible(false);

    assertThat(passwordView)
        .hasHiddenPassword()
        .hasToggleHiddenAlpha()
        .hasDefaultToggle();
  }

  //TODO setTintColor
  //TODO setTintColorRes

  @Test
  public void givenDrawable_whenSetShownDrawable_thenToggleShownDrawableUpdated() {
    passwordView.setPasswordVisible(true);

    passwordView.setShownDrawable(getDrawable(R.drawable.ic_icon_square));

    assertThat(passwordView)
        .hasToggle(R.drawable.ic_icon_square);
  }

  @Test
  public void givenDrawableResource_whenSetShownDrawable_thenToggleShownDrawableUpdated() {
    passwordView.setPasswordVisible(true);

    passwordView.setShownDrawable(R.drawable.ic_icon_square);

    assertThat(passwordView)
        .hasToggle(R.drawable.ic_icon_square);
  }

  @Test
  public void givenDrawable_whenSetHiddenDrawable_thenToggleHiddenDrawableUpdated() {
    passwordView.setHiddenDrawable(getDrawable(R.drawable.ic_avatar_circle));

    assertThat(passwordView)
        .hasToggle(R.drawable.ic_avatar_circle);
  }

  @Test
  public void givenDrawableResource_whenSetHiddenDrawable_thenToggleHiddenDrawableUpdated() {
    passwordView.setHiddenDrawable(R.drawable.ic_avatar_circle);

    assertThat(passwordView)
        .hasToggle(R.drawable.ic_avatar_circle);
  }

  @Test
  public void givenOpacity_whenSetToggleType_thenToggleOpacityChanged() {
    passwordView.setPasswordVisible(false);

    passwordView.setToggleType(PasswordEditText.TOGGLE_OPACITY);

    assertThat(passwordView)
        .hasToggleHiddenAlpha()
        .hasDefaultToggle();
  }

  @Test
  public void givenStrikeThrough_whenSetToggleType_thenToggleDrawableChanged() {
    passwordView.setPasswordVisible(false);

    passwordView.setToggleType(PasswordEditText.TOGGLE_STRIKETHROUGH);

    assertThat(passwordView)
        .hasToggleShownAlpha()
        .hasStrikethroughToggle();
  }

  @Test
  public void whenSetTintColor_thenDrawableTinted() {
    passwordView.setTintColor(Color.RED);

    assertThat(passwordView)
        .hasTintColor(Color.RED);
  }

  @Test
  public void whenSetTintColorRes_thenDrawableTinted() {
    passwordView.setTintColorRes(R.color.ma_amber_100);

    assertThat(passwordView)
        .hasTintColorRes(R.color.ma_amber_100);
  }

  private Drawable getDrawable(@DrawableRes int drawableRes) {
    return ContextCompat.getDrawable(RuntimeEnvironment.application, drawableRes);
  }

  private void fireActionUpTouchEvent(View view, float xPosition, float yPosition) {
    fireTouchEvent(view, xPosition, yPosition, MotionEvent.ACTION_UP);
  }

  private void fireActionDownTouchEvent(View view, float xPosition, float yPosition) {
    fireTouchEvent(view, xPosition, yPosition, MotionEvent.ACTION_DOWN);
  }

  private void fireTouchEvent(View view, float xPosition, float yPosition, int actionDown) {
    MotionEvent motionEvent = MotionEvent.obtain(
        SystemClock.uptimeMillis(),
        SystemClock.uptimeMillis(),
        actionDown,
        xPosition,
        yPosition,
        0
    );
    view.dispatchTouchEvent(motionEvent);
  }

}