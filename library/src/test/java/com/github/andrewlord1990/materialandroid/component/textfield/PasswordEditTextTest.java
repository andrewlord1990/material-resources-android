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

package com.github.andrewlord1990.materialandroid.component.textfield;

import android.content.res.Resources;
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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.internal.ShadowExtractor;
import org.robolectric.res.Attribute;
import org.robolectric.res.ResourceLoader;
import org.robolectric.shadows.RoboAttributeSet;
import org.robolectric.shadows.ShadowResources;

import java.util.ArrayList;
import java.util.List;

import static com.github.andrewlord1990.materialandroid.component.textfield.PasswordEditTextAssert.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
public class PasswordEditTextTest {

    private static final String PACKAGE = "com.github.andrewlord1990.materialandroid";

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
        //When
        PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application);

        //Then
        assertThat(actual)
                .hasHiddenPassword()
                .hasDefaultToggle()
                .hasToggleHiddenAlpha();
    }

    @Test
    public void givenAttrsWithNoCustomAttributes_whenCreated_thenSetupWithDefaults() {
        //Given
        AttributeSet attrs = createAttributeSet(new ArrayList<Attribute>());

        //When
        PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application, attrs);

        //Then
        assertThat(actual)
                .hasDefaultToggle()
                .hasToggleHiddenAlpha()
                .hasHiddenPassword();
    }

    @Test
    public void givenCustomIconsInAttrs_whenCreated_thenHasCustomHiddenIcon() {
        //Given
        AttributeSet attrs = createCustomIconsAttributeSet();

        //When
        PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application, attrs);

        //Then
        assertThat(actual).hasToggle(R.drawable.ic_avatar_circle);
    }

    @Test
    public void givenCustomIconsInAttrs_whenCreatedAndVisibilityToggled_thenHasCustomShownIcon() {
        //Given
        AttributeSet attrs = createCustomIconsAttributeSet();

        //When
        PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application, attrs, 0);
        actual.togglePasswordVisibility();

        //Then
        assertThat(actual).hasToggle(R.drawable.ic_icon_square);
    }

    @Test
    public void givenStrikeThroughTypeInAttrs_whenCreated_thenHasStrikeThroughToggle() {
        //Given
        AttributeSet attrs = createStrikeThroughAttributeSet();

        //When
        PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application, attrs);

        //Then
        assertThat(actual).hasStrikethroughToggle();
    }

    @Test
    public void givenStrikeThroughTypeInAttrs_whenCreatedAndVisibilityToggled_thenHasDefaultToggle() {
        //Given
        AttributeSet attrs = createStrikeThroughAttributeSet();

        //When
        PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application, attrs, 0);
        actual.togglePasswordVisibility();

        //Then
        assertThat(actual)
                .hasDefaultToggle()
                .hasToggleShownAlpha();
    }

    @Test
    public void givenPasswordShownInAttrs_whenCreated_thenPasswordShown() {
        //Given
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(createAttribute("md_password_shown", "true"));
        AttributeSet attrs = createAttributeSet(attributes);

        //When
        PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application, attrs);

        //Then
        assertThat(actual)
                .hasVisiblePassword()
                .hasToggleShownAlpha();
    }

    private AttributeSet createStrikeThroughAttributeSet() {
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(createAttribute("md_password_toggle_type", "strikethrough"));
        return createAttributeSet(attributes);
    }

    private AttributeSet createCustomIconsAttributeSet() {
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(createAttribute("md_password_hidden_drawable", "@drawable/ic_avatar_circle"));
        attributes.add(createAttribute("md_password_shown_drawable", "@drawable/ic_icon_square"));
        return createAttributeSet(attributes);
    }

    private Attribute createAttribute(String name, String value) {
        return new Attribute(PACKAGE + ":attr/" + name, value, PACKAGE);
    }

    private AttributeSet createAttributeSet(List<Attribute> attributes) {
        Resources resources = RuntimeEnvironment.application.getResources();
        ResourceLoader resourceLoader = shadowOf(resources).getResourceLoader();
        return new RoboAttributeSet(attributes, resourceLoader);
    }

    private static ShadowResources shadowOf(Resources actual) {
        return (ShadowResources) ShadowExtractor.extract(actual);
    }

    @Test
    public void whenSetInputType_thenTypefaceUnchanged() {
        //Given
        passwordView.setTypeface(Typeface.DEFAULT_BOLD);

        //When
        passwordView.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

        //Then
        assertThat(passwordView)
                .hasInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                .hasTypeface(Typeface.DEFAULT_BOLD);
    }

    @Test
    public void whenSetInputType_thenSelectionUnchanged() {
        //Given
        int start = 10;
        int end = 12;
        passwordView.setText("a really long string with lots of content");
        passwordView.setSelection(start, end);

        //When
        passwordView.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        //Then
        assertThat(passwordView)
                .hasInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                .hasSelectionStart(start)
                .hasSelectionEnd(end);
    }

    @Test
    public void givenLeftToRightTouchWithinToggle_whenOnTouchEvent_thenPasswordVisibilityToggled() {
        //Given
        passwordView.setPasswordVisible(false);
        float x = passwordView.getRight() - 10;
        float y = passwordView.getTop() + 10;

        //When
        fireActionUpTouchEvent(passwordView, x, y);

        //Then
        assertThat(passwordView)
                .hasVisiblePassword();
    }

    @Test
    @Config(sdk = 16)
    public void givenLessThanSdk17TouchWithinToggle_whenOnTouchEvent_thenPasswordVisibilityToggled() {
        //Given
        ViewCompat.setLayoutDirection(passwordView, ViewCompat.LAYOUT_DIRECTION_RTL);
        passwordView.setPasswordVisible(false);
        float x = passwordView.getRight() - 10;
        float y = passwordView.getTop() + 10;

        //When
        fireActionUpTouchEvent(passwordView, x, y);

        //Then
        assertThat(passwordView)
                .hasVisiblePassword();
    }

    @Test
    public void givenTouchOutsideToggle_whenOnTouchEvent_thenPasswordVisibilityNotToggled() {
        //Given
        passwordView.setPasswordVisible(false);
        float x = passwordView.getLeft() + 10;
        float y = passwordView.getTop() + 10;

        //When
        fireActionUpTouchEvent(passwordView, x, y);

        //Then
        assertThat(passwordView)
                .hasHiddenPassword();
    }

    @Test
    public void givenTouchActionDown_whenOnTouchEvent_thenPasswordVisibilityNotToggled() {
        //Given
        passwordView.setPasswordVisible(false);
        float x = passwordView.getLeft() + 10;
        float y = passwordView.getTop() + 10;

        //When
        fireActionDownTouchEvent(passwordView, x, y);

        //Then
        assertThat(passwordView)
                .hasHiddenPassword();
    }

    @Test
    public void givenPasswordCurrentlyVisible_whenTogglePasswordVisibility_thenPasswordHidden() {
        //Given
        passwordView.setPasswordVisible(true);

        //When
        passwordView.togglePasswordVisibility();

        //Then
        assertThat(passwordView)
                .hasHiddenPassword()
                .hasToggleHiddenAlpha()
                .hasDefaultToggle();
    }

    @Test
    public void givenPasswordCurrentlyHidden_whenTogglePasswordVisibility_thenPasswordShown() {
        //Given
        passwordView.setPasswordVisible(false);

        //When
        passwordView.togglePasswordVisibility();

        //Then
        assertThat(passwordView)
                .hasVisiblePassword()
                .hasToggleShownAlpha()
                .hasDefaultToggle();
    }

    @Test
    public void givenBeingSetToVisible_whenSetPasswordVisible_thenPasswordShownWithShownToggle() {
        //When
        passwordView.setPasswordVisible(true);

        //Then
        assertThat(passwordView)
                .hasVisiblePassword()
                .hasToggleShownAlpha()
                .hasDefaultToggle();
    }

    @Test
    public void givenBeingSetToHidden_whenSetPasswordVisible_thenPasswordHiddenWithHiddenToggle() {
        //Given
        passwordView.setPasswordVisible(true);

        //When
        passwordView.setPasswordVisible(false);

        //Then
        assertThat(passwordView)
                .hasHiddenPassword()
                .hasToggleHiddenAlpha()
                .hasDefaultToggle();
    }

    //TODO setTintColor
    //TODO setTintColorRes

    @Test
    public void givenDrawable_whenSetShownDrawable_thenToggleShownDrawableUpdated() {
        //Given
        passwordView.setPasswordVisible(true);

        //When
        passwordView.setShownDrawable(getDrawable(R.drawable.ic_icon_square));

        //Then
        assertThat(passwordView)
                .hasToggle(R.drawable.ic_icon_square);
    }

    @Test
    public void givenDrawableResource_whenSetShownDrawable_thenToggleShownDrawableUpdated() {
        //Given
        passwordView.setPasswordVisible(true);

        //When
        passwordView.setShownDrawable(R.drawable.ic_icon_square);

        //Then
        assertThat(passwordView)
                .hasToggle(R.drawable.ic_icon_square);
    }

    @Test
    public void givenDrawable_whenSetHiddenDrawable_thenToggleHiddenDrawableUpdated() {
        //When
        passwordView.setHiddenDrawable(getDrawable(R.drawable.ic_avatar_circle));

        //Then
        assertThat(passwordView)
                .hasToggle(R.drawable.ic_avatar_circle);
    }

    @Test
    public void givenDrawableResource_whenSetHiddenDrawable_thenToggleHiddenDrawableUpdated() {
        //When
        passwordView.setHiddenDrawable(R.drawable.ic_avatar_circle);

        //Then
        assertThat(passwordView)
                .hasToggle(R.drawable.ic_avatar_circle);
    }

    @Test
    public void givenOpacity_whenSetToggleType_thenToggleOpacityChanged() {
        //Given
        passwordView.setPasswordVisible(false);

        //When
        passwordView.setToggleType(PasswordEditText.TOGGLE_OPACITY);

        //Then
        assertThat(passwordView)
                .hasToggleHiddenAlpha()
                .hasDefaultToggle();
    }

    @Test
    public void givenStrikeThrough_whenSetToggleType_thenToggleDrawableChanged() {
        //Given
        passwordView.setPasswordVisible(false);

        //When
        passwordView.setToggleType(PasswordEditText.TOGGLE_STRIKETHROUGH);

        //Then
        assertThat(passwordView)
                .hasToggleShownAlpha()
                .hasStrikethroughToggle();
    }

    @Test
    public void whenSetTintColor_thenDrawableTinted() {
        //When
        passwordView.setTintColor(Color.RED);

        //Then
        assertThat(passwordView)
                .hasTintColor(Color.RED);
    }

    @Test
    public void whenSetTintColorRes_thenDrawableTinted() {
        //When
        passwordView.setTintColorRes(R.color.md_amber_100);

        //Then
        assertThat(passwordView)
                .hasTintColorRes(R.color.md_amber_100);
    }

    private Drawable getDrawable(@DrawableRes int drawableRes) {
        return ContextCompat.getDrawable(RuntimeEnvironment.application, drawableRes);
    }

    private void fireActionUpTouchEvent(View view, float x, float y) {
        fireTouchEvent(view, x, y, MotionEvent.ACTION_UP);
    }

    private void fireActionDownTouchEvent(View view, float x, float y) {
        fireTouchEvent(view, x, y, MotionEvent.ACTION_DOWN);
    }

    private void fireTouchEvent(View view, float x, float y, int actionDown) {
        MotionEvent motionEvent = MotionEvent.obtain(
                SystemClock.uptimeMillis(),
                SystemClock.uptimeMillis(),
                actionDown,
                x,
                y,
                0
        );
        view.dispatchTouchEvent(motionEvent);
    }

}