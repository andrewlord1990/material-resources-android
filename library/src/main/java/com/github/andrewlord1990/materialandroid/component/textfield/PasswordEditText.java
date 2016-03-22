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

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.github.andrewlord1990.materialandroid.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//TODO Add comments to public API methods
public class PasswordEditText extends AppCompatEditText {

    private static final int TOGGLE_OPACTITY = 0;
    private static final int TOGGLE_STRIKETHROUGH = 1;

    @IntDef({TOGGLE_OPACTITY, TOGGLE_STRIKETHROUGH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ToggleType {
    }

    private final static int ALPHA_SHOWN = (int) (255 * 0.54f);
    private final static int ALPHA_HIDDEN = (int) (255 * 0.38f);

    private Drawable shownIcon;
    private Drawable hiddenIcon;

    @ColorInt
    private int tintColor;

    private boolean passwordVisible;

    public PasswordEditText(Context context) {
        super(context);
        loadDefaults();
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadThemeAttributes(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadThemeAttributes(attrs);
    }

    private void loadThemeAttributes(AttributeSet attrs) {
        TypedArray typedAttrs = getContext().getTheme().obtainStyledAttributes(
                attrs, R.styleable.MDPasswordEditText, R.attr.mdPasswordEditTextStyle, 0);
        try {
            loadIcons(typedAttrs);
            loadToggleType(typedAttrs);
            loadToggleTintColor(typedAttrs);
            loadPasswordShown(typedAttrs);
        } finally {
            typedAttrs.recycle();
        }
        setPasswordVisibility();
    }

    private void loadIcons(TypedArray attrs) {
        shownIcon = attrs.getDrawable(R.styleable.MDPasswordEditText_md_password_shown_drawable);
        hiddenIcon = attrs.getDrawable(R.styleable.MDPasswordEditText_md_password_hidden_drawable);
    }

    private void loadToggleType(TypedArray attrs) {
        int type = attrs.getInt(
                R.styleable.MDPasswordEditText_md_password_toggle_type, TOGGLE_OPACTITY);
        if (shownIcon == null) {
            setShownIcon();
        }
        if (hiddenIcon == null) {
            setHiddenIconFromType(type);
        }
    }

    private void setShownIcon() {
        shownIcon = getIcon(R.drawable.ic_password_visibility_default);
        shownIcon.setAlpha(ALPHA_SHOWN);
    }

    private void setHiddenIconFromType(int type) {
        if (type == TOGGLE_OPACTITY) {
            hiddenIcon = getIcon(R.drawable.ic_password_visibility_default);
            hiddenIcon.setAlpha(ALPHA_HIDDEN);
        } else if (type == TOGGLE_STRIKETHROUGH) {
            hiddenIcon = getIcon(R.drawable.ic_password_visibility_strikethrough);
            hiddenIcon.setAlpha(ALPHA_SHOWN);
        }
    }

    private Drawable getIcon(@DrawableRes int drawable) {
        return ContextCompat.getDrawable(getContext(), drawable).mutate();
    }

    private void loadToggleTintColor(TypedArray attrs) {
        tintColor = attrs.getColor(R.styleable.MDPasswordEditText_md_drawable_tint_color, 0);
    }

    private void loadPasswordShown(TypedArray attrs) {
        passwordVisible = attrs.getBoolean(R.styleable.MDPasswordEditText_md_password_shown, false);
    }

    private void loadDefaults() {
        setShownIcon();
        setHiddenIconFromType(TOGGLE_OPACTITY);
        setPasswordVisibility();
    }

    private void setPasswordVisibility() {
        setPasswordInputType();
        setDrawables();
    }

    private void setDrawables() {
        Drawable[] drawables = getDrawables();
        if (passwordVisible) {
            drawables[2] = shownIcon;
        } else {
            drawables[2] = hiddenIcon;
        }
        TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(this,
                drawables[0], drawables[1], tintDrawable(drawables[2]), drawables[3]);
    }

    private Drawable tintDrawable(Drawable drawable) {
        if (tintColor != 0) {
            Drawable wrapper = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(wrapper, tintColor);
            return wrapper;
        }
        return drawable;
    }

    private void setPasswordInputType() {
        if (passwordVisible) {
            setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    @Override
    public void setInputType(int type) {
        Typeface typeface = getTypeface();
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();

        super.setInputType(type);

        setTypeface(typeface);
        setSelection(selectionStart, selectionEnd);
    }

    Drawable[] getDrawables() {
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) {
            return getCompoundDrawablesRelative();
        }
        return getCompoundDrawables();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isTouchEventWithinToggle(event)) {
                togglePasswordVisibility();
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    private boolean isTouchEventWithinToggle(MotionEvent event) {
        int x = (int) event.getX();
        Rect bounds = getDrawables()[2].getBounds();
        boolean withinIconLeftToRight = isLeftToRight() && (x >= getRight() - bounds.width());
        boolean withinIconRightToLeft = !isLeftToRight() && (x <= getLeft() + bounds.width());
        return withinIconLeftToRight || withinIconRightToLeft;
    }

    private boolean isLeftToRight() {
        if (VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return true;
        }
        Configuration config = getResources().getConfiguration();
        return !(config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL);
    }

    public boolean isPasswordVisible() {
        return passwordVisible;
    }

    public void togglePasswordVisibility() {
        setPasswordVisible(!passwordVisible);
    }

    public void setPasswordVisible(boolean visible) {
        passwordVisible = visible;
        setPasswordVisibility();
    }

    @ColorInt
    public int getTintColor() {
        return tintColor;
    }

    public void setTintColor(@ColorInt int tintColor) {
        this.tintColor = tintColor;
        setPasswordVisibility();
    }

    public void setTintColorRes(@ColorRes int tintColorRes) {
        this.tintColor = ContextCompat.getColor(getContext(), tintColorRes);
        setPasswordVisibility();
    }

    public void setShownDrawable(Drawable shownDrawable) {
        shownIcon = shownDrawable;
        setPasswordVisibility();
    }

    public void setShownDrawable(@DrawableRes int shownDrawableRes) {
        shownIcon = ContextCompat.getDrawable(getContext(), shownDrawableRes);
        setPasswordVisibility();
    }

    public void setHiddenDrawable(Drawable hiddenDrawable) {
        hiddenIcon = hiddenDrawable;
        setPasswordVisibility();
    }

    public void setHiddenDrawable(@DrawableRes int hiddenDrawableRes) {
        hiddenIcon = ContextCompat.getDrawable(getContext(), hiddenDrawableRes);
        setPasswordVisibility();
    }

    public void setToggleType(@ToggleType int toggleType) {
        setShownIcon();
        setHiddenIconFromType(toggleType);
        setPasswordVisibility();
    }

}
