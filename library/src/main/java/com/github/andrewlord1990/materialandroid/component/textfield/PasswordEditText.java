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
import android.view.inputmethod.EditorInfo;

import com.github.andrewlord1990.materialandroid.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * An EditText view which meets the Material Design specification for a password input field. It
 * features a visibility toggle that switches between showing plain text and showing asterisk
 * characters instead. The password is hidden by default, however, this can be changed using the
 * md_password_shown attribute.
 * The view can be customised through various setter methods, XML attributes passed to the view or
 * by assigning a style to use for all PasswordEditTexts to the mdPasswordEditTextStyle theme
 * attribute.
 */
public class PasswordEditText extends AppCompatEditText {

    public static final int TOGGLE_OPACITY = 0;
    public static final int TOGGLE_STRIKETHROUGH = 1;

    @IntDef({TOGGLE_OPACITY, TOGGLE_STRIKETHROUGH})
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

    /**
     * Create a PasswordEditText view using default settings, which can be customised later.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public PasswordEditText(Context context) {
        super(context);
        loadDefaults();
    }

    /**
     * Create a PasswordEditText view through XML inflation using settings from provided
     * attributes and from the style assigned to the theme attribute mdPasswordEditTextStyle.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     */
    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadThemeAttributes(attrs);
    }

    /**
     * Create a PasswordEditText view through XML inflation using settings from provided
     * attributes and from the style assigned to the specified theme attribute.
     *
     * @param context      The Context the view is running in, through which it can
     *                     access the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     *                     the view. Can be 0 to not look for defaults.
     */
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
                R.styleable.MDPasswordEditText_md_password_toggle_type, TOGGLE_OPACITY);
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
        if (type == TOGGLE_OPACITY) {
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
        tintColor = attrs.getColor(R.styleable.MDPasswordEditText_md_password_toggle_tint_color, 0);
    }

    private void loadPasswordShown(TypedArray attrs) {
        passwordVisible = attrs.getBoolean(R.styleable.MDPasswordEditText_md_password_shown, false);
    }

    private void loadDefaults() {
        setShownIcon();
        setHiddenIconFromType(TOGGLE_OPACITY);
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

    /**
     * Set the type of the content with a constant as defined for {@link EditorInfo#inputType}.
     * When the input type is set to be a password, by default Android sets the typeface of the
     * view to Monospace. This view instead will maintain whichever typeface the view currently
     * has, meaning if you set a custom typeface it won't be lost when changing the input type. It
     * also maintains the position of the insertion point within the EditText.
     *
     * @param type The input type to apply to this view.
     * @see android.text.InputType
     */
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

    /**
     * This will be fired when a touch-screen motion event occurs. It is used to handle the
     * visibility toggle being pressed on.
     *
     * @param event The touch-screen motion event which has occurred.
     * @return Whether the event has been consumed. It will be consumed if the visibility toggle
     * has been pressed.
     */
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
        int left = getViewLeft();
        int right = left + getMeasuredWidth();
        int x = (int) event.getX() + left;
        Rect bounds = getDrawables()[2].getBounds();
        boolean withinIconLeftToRight = isLeftToRight() && (x >= right - bounds.width());
        boolean withinIconRightToLeft = !isLeftToRight() && (x <= left + bounds.width());
        return withinIconLeftToRight || withinIconRightToLeft;
    }

    private int getViewLeft() {
        int[] viewPosition = new int[2];
        getLocationOnScreen(viewPosition);
        return viewPosition[0];
    }

    private boolean isLeftToRight() {
        if (VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return true;
        }
        Configuration config = getResources().getConfiguration();
        return !(config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL);
    }

    /**
     * Returns whether the password is currently visible within the view.
     *
     * @return Whether the password is currently visible.
     */
    public boolean isPasswordVisible() {
        return passwordVisible;
    }

    /**
     * Toggle the visibility of the password. If it is current visible, it will hidden and
     * replaced with asterisk characters. If it is currently hidden then it will be displayed in
     * plain text.
     */
    public void togglePasswordVisibility() {
        setPasswordVisible(!passwordVisible);
    }

    /**
     * Set the visibility of the password.
     *
     * @param visible Whether the password should be displayed in plain text.
     */
    public void setPasswordVisible(boolean visible) {
        passwordVisible = visible;
        setPasswordVisibility();
    }

    /**
     * Get the tint color which be applied to the visibility toggle. This was assigned either
     * through the setter, an XML attribute when the view was inflated or through the global style.
     *
     * @return The visibility toggle tint color.
     */
    @ColorInt
    public int getTintColor() {
        return tintColor;
    }

    /**
     * Set the tint color to apply to the visibility toggle to the specified color value.
     *
     * @param tintColor The tint color to apply.
     */
    public void setTintColor(@ColorInt int tintColor) {
        this.tintColor = tintColor;
        setPasswordVisibility();
    }

    /**
     * Set the tint color to apply to the visibility toggle to the color linked to by the
     * specified color resource.
     *
     * @param tintColorRes The resource of the tint color to apply.
     */
    public void setTintColorRes(@ColorRes int tintColorRes) {
        this.tintColor = ContextCompat.getColor(getContext(), tintColorRes);
        setPasswordVisibility();
    }

    /**
     * Set the drawable to use for the visibility toggle when the password is currently being
     * shown.
     *
     * @param shownDrawable Drawable to use for visibility toggle whilst password is being shown.
     */
    public void setShownDrawable(Drawable shownDrawable) {
        shownIcon = shownDrawable;
        setPasswordVisibility();
    }

    /**
     * Set the drawable to use for the visibility toggle when the password is currently being
     * shown.
     *
     * @param shownDrawableRes Drawable to use for visibility toggle whilst password is being shown.
     */
    public void setShownDrawable(@DrawableRes int shownDrawableRes) {
        shownIcon = ContextCompat.getDrawable(getContext(), shownDrawableRes);
        setPasswordVisibility();
    }

    /**
     * Set the drawable to use for the visibility toggle when the password is currently hidden and
     * being displayed as asterisk characters.
     *
     * @param hiddenDrawable Drawable to use for visibility toggle whilst password is being hidden.
     */
    public void setHiddenDrawable(Drawable hiddenDrawable) {
        hiddenIcon = hiddenDrawable;
        setPasswordVisibility();
    }

    /**
     * Set the drawable to use for the visibility toggle when the password is currently hidden and
     * being displayed as asterisk characters.
     *
     * @param hiddenDrawableRes Drawable to use for visibility toggle whilst password is being hidden.
     */
    public void setHiddenDrawable(@DrawableRes int hiddenDrawableRes) {
        hiddenIcon = ContextCompat.getDrawable(getContext(), hiddenDrawableRes);
        setPasswordVisibility();
    }

    /**
     * Set the type of visibility toggle to use. When the password is hidden, then either change
     * the toggle's opacity or replace it with an icon with a strike through it.
     *
     * @param toggleType The type of visibility toggle to use.
     */
    public void setToggleType(@ToggleType int toggleType) {
        setShownIcon();
        setHiddenIconFromType(toggleType);
        setPasswordVisibility();
    }

}
