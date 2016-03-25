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
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.github.andrewlord1990.materialandroid.R;

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

import static com.github.andrewlord1990.materialandroid.component.textfield.PasswordEditTextAssert.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
public class PasswordEditTextTest {

    private static final String PACKAGE = "com.github.andrewlord1990.materialandroid";

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
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

    //TODO Create with API < 17 (tests getting drawables and LTR layout))

    //TODO setInputType

    //TODO onTouchEvent

    //TODO togglePasswordVisibility
    //TODO setPasswordVisible

    //TODO setTintColor
    //TODO setTintColorRes

    @Test
    public void givenDrawable_whenSetShownDrawable_thenToggleShownDrawableUpdated() {
        //Given
        PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application);
        actual.setPasswordVisible(true);

        //When
        actual.setShownDrawable(getDrawable(R.drawable.ic_icon_square));

        //Then
        assertThat(actual)
                .hasToggle(R.drawable.ic_icon_square);
    }

    @Test
    public void givenDrawableResource_whenSetShownDrawable_thenToggleShownDrawableUpdated() {
        //Given
        PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application);
        actual.setPasswordVisible(true);

        //When
        actual.setShownDrawable(R.drawable.ic_icon_square);

        //Then
        assertThat(actual)
                .hasToggle(R.drawable.ic_icon_square);
    }

    @Test
    public void givenDrawable_whenSetHiddenDrawable_thenToggleHiddenDrawableUpdated() {
        //Given
        PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application);

        //When
        actual.setHiddenDrawable(getDrawable(R.drawable.ic_avatar_circle));

        //Then
        assertThat(actual)
                .hasToggle(R.drawable.ic_avatar_circle);
    }

    @Test
    public void givenDrawableResource_whenSetHiddenDrawable_thenToggleHiddenDrawableUpdated() {
        //Given
        PasswordEditText actual = new PasswordEditText(RuntimeEnvironment.application);

        //When
        actual.setHiddenDrawable(R.drawable.ic_avatar_circle);

        //Then
        assertThat(actual)
                .hasToggle(R.drawable.ic_avatar_circle);
    }

    //TODO setToggleType

    private Drawable getDrawable(@DrawableRes int drawableRes) {
        return ContextCompat.getDrawable(RuntimeEnvironment.application, drawableRes);
    }

}