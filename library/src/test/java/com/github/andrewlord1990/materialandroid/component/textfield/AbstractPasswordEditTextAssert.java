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

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.InputType;

import com.github.andrewlord1990.materialandroid.R;

import org.assertj.android.api.widget.AbstractTextViewAssert;
import org.assertj.core.api.Assertions;
import org.robolectric.internal.ShadowExtractor;
import org.robolectric.shadows.ShadowDrawable;

import static org.assertj.android.api.Assertions.assertThat;

public abstract class AbstractPasswordEditTextAssert<S extends AbstractPasswordEditTextAssert<S, A>,
        A extends PasswordEditText> extends AbstractTextViewAssert<S, A> {

    private final static int ALPHA_SHOWN = (int) (255 * 0.54f);
    private final static int ALPHA_HIDDEN = (int) (255 * 0.38f);

    protected AbstractPasswordEditTextAssert(A actual, Class<S> selfType) {
        super(actual, selfType);
    }

    public AbstractPasswordEditTextAssert hasHiddenPassword() {
        isNotNull();
        assertThat(actual)
                .overridingErrorMessage("Expected password hidden but was visible.")
                .hasInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        Assertions.assertThat(actual.isPasswordVisible()).isFalse();
        return myself;
    }

    public AbstractPasswordEditTextAssert hasVisiblePassword() {
        isNotNull();
        assertThat(actual)
                .overridingErrorMessage("Expected password visible but was hidden.")
                .hasInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        Assertions.assertThat(actual.isPasswordVisible()).isTrue();
        return myself;
    }

    public AbstractPasswordEditTextAssert hasDefaultToggle() {
        isNotNull();
        assertThatToggleHasResId(R.drawable.ic_password_visibility_default);
        return myself;
    }

    public AbstractPasswordEditTextAssert hasStrikethroughToggle() {
        isNotNull();
        assertThatToggleHasResId(R.drawable.ic_password_visibility_strikethrough);
        hasToggleShownAlpha();
        return myself;
    }

    public AbstractPasswordEditTextAssert hasToggle(@DrawableRes int drawableRes) {
        isNotNull();
        assertThatToggleHasResId(drawableRes);
        return myself;
    }

    public AbstractPasswordEditTextAssert hasToggleShownAlpha() {
        isNotNull();
        Drawable[] drawables = actual.getDrawables();
        assertThat(drawables[2])
                .isNotNull()
                .hasAlpha(ALPHA_SHOWN);
        return myself;
    }

    public AbstractPasswordEditTextAssert hasToggleHiddenAlpha() {
        isNotNull();
        Drawable[] drawables = actual.getDrawables();
        assertThat(drawables[2])
                .isNotNull()
                .hasAlpha(ALPHA_HIDDEN);
        return myself;
    }

    public AbstractPasswordEditTextAssert hasTintColorRes(@ColorRes int tintColorRes) {
        isNotNull();
        Assertions.assertThat(actual.getTintColor())
                .isEqualTo(ContextCompat.getColor(actual.getContext(), tintColorRes));
        return myself;
    }

    public AbstractPasswordEditTextAssert hasTintColor(@ColorInt int tintColor) {
        isNotNull();
        Assertions.assertThat(actual.getTintColor()).isEqualTo(tintColor);
        return myself;
    }

    private void assertThatToggleHasResId(@DrawableRes int resId) {
        Drawable[] drawables = actual.getDrawables();
        ShadowDrawable shadow = shadowOf(drawables[2]);
        int actualResId = shadow.getCreatedFromResId();
        Assertions.assertThat(actualResId)
                .overridingErrorMessage("Expected toggle <%s> but was <%s>.",
                        actual.getResources().getResourceEntryName(resId),
                        actual.getResources().getResourceEntryName(actualResId))
                .isNotNull()
                .isEqualTo(resId);

    }

    public static ShadowDrawable shadowOf(Drawable actual) {
        return (ShadowDrawable) ShadowExtractor.extract(actual);
    }

}
