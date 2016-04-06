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

package com.github.andrewlord1990.materialandroid.component.grid;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.andrewlord1990.materialandroid.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A view that meets the Material Design specification for a grid item view. It supports many
 * different variants, containing one or two lines of text and an icon at the start (left) or end
 * (right). When using two lines of text, the lines of text can either have the same text size
 * or different text sizes.
 */
public class GridItemView extends FrameLayout {

    public static final int VARIANT_ONE_LINE_TEXT = 0;
    public static final int VARIANT_ONE_LINE_TEXT_ICON = 1;
    public static final int VARIANT_TWO_LINE_TEXT = 2;
    public static final int VARIANT_TWO_LINE_TEXT_ICON = 3;

    @IntDef({VARIANT_ONE_LINE_TEXT, VARIANT_ONE_LINE_TEXT_ICON,
            VARIANT_TWO_LINE_TEXT, VARIANT_TWO_LINE_TEXT_ICON})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GridItemVariant {
    }

    public static final int ICON_GRAVITY_START = 0;
    public static final int ICON_GRAVITY_END = 1;

    @IntDef({ICON_GRAVITY_START, ICON_GRAVITY_END})
    @Retention(RetentionPolicy.SOURCE)
    public @interface IconGravity {
    }

    private TextView primaryTextView;
    private TextView secondaryTextView;
    private ImageView iconView;

    private CharSequence primaryText;
    private CharSequence secondaryText;
    private Drawable icon;

    @ColorInt
    private int primaryTextColor;

    @ColorInt
    private int secondaryTextColor;

    private ColorStateList primaryTextColorStateList;
    private ColorStateList secondaryTextColorStateList;

    @GridItemVariant
    private int variant;

    @IconGravity
    private int iconGravity;

    /**
     * Create a grid item view using the default settings, which can then be customised later.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public GridItemView(Context context) {
        super(context);
        loadDefaults();
    }

    /**
     * Create a grid item view through XML inflation using settings from provided
     * attributes and from the style assigned to the theme attribute mdGridItemViewStyle.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     */
    public GridItemView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.mdGridItemViewStyle);
    }

    /**
     * Create a grid item view through XML inflation using settings from provided
     * attributes and from the style assigned to the specified theme attribute.
     *
     * @param context      The Context the view is running in, through which it can
     *                     access the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     *                     the view. Can be 0 to not look for defaults.
     */
    public GridItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadThemeAttributes(attrs, defStyleAttr, 0);
    }

    /**
     * Create a grid item view through XML inflation using settings from provided
     * attributes, from the style assigned to the specified theme attribute and from the
     * specified style.
     *
     * @param context      The Context the view is running in, through which it can
     *                     access the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     *                     the view. Can be 0 to not look for defaults.
     * @param defStyleRes  A resource identifier of a style resource that
     *                     supplies default values for the view, used only if
     *                     defStyleAttr is 0 or can not be found in the theme. Can be 0
     *                     to not look for defaults.
     */
    @TargetApi(21)
    public GridItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        loadThemeAttributes(attrs, defStyleAttr, defStyleRes);
    }

    private void loadThemeAttributes(AttributeSet attrs, @AttrRes int themeAttribute, @StyleRes int styleRes) {
        TypedArray typedAttrs = getContext().getTheme().obtainStyledAttributes(
                attrs, R.styleable.MDGridItemView, themeAttribute, styleRes);
        try {
            loadIcon(typedAttrs);
            loadVariant(typedAttrs);
            loadText(typedAttrs);
            loadTextColors(typedAttrs);
        } finally {
            typedAttrs.recycle();
        }
    }

    private void loadIcon(TypedArray typedAttrs) {
        Drawable icon = typedAttrs.getDrawable(R.styleable.MDGridItemView_md_grid_icon);
        setIcon(icon);
        @IconGravity
        int iconGravity = typedAttrs.getInt(
                R.styleable.MDGridItemView_md_grid_icon_gravity, ICON_GRAVITY_START);
        this.iconGravity = iconGravity;
    }

    private void loadVariant(TypedArray typedAttrs) {
        @GridItemVariant
        int variant = typedAttrs.getInt(R.styleable.MDGridItemView_md_grid_item_variant,
                VARIANT_ONE_LINE_TEXT);
        setVariant(variant);
    }

    private void loadText(TypedArray typedAttrs) {
        String primaryText = typedAttrs.getString(R.styleable.MDGridItemView_md_grid_primary_text);
        setPrimaryText(primaryText);
        String secondaryText = typedAttrs.getString(R.styleable.MDGridItemView_md_grid_secondary_text);
        setSecondaryText(secondaryText);
    }

    private void loadTextColors(TypedArray typedAttrs) {
        int primaryTextColor = typedAttrs.getColor(R.styleable.MDGridItemView_md_grid_primary_text_color, 0);
        if (primaryTextColor != 0) {
            setPrimaryTextColor(primaryTextColor);
        }
        int secondaryTextColor = typedAttrs.getColor(R.styleable.MDGridItemView_md_grid_secondary_text_color, 0);
        if (secondaryTextColor != 0) {
            setSecondaryTextColor(secondaryTextColor);
        }
    }

    private void loadDefaults() {
        iconGravity = ICON_GRAVITY_START;
        setVariant(VARIANT_ONE_LINE_TEXT);
    }

    @LayoutRes
    private int getLayoutFromVariant(@GridItemVariant int variant) {
        switch (variant) {
            case VARIANT_ONE_LINE_TEXT:
                return R.layout.md_grid_list_label_single_line;
            case VARIANT_ONE_LINE_TEXT_ICON:
                return getOneLineIconLayout();
            case VARIANT_TWO_LINE_TEXT:
                return R.layout.md_grid_list_label_two_line_same;
            case VARIANT_TWO_LINE_TEXT_ICON:
                return getTwoLineIconLayout();
            default:
                return R.layout.md_grid_list_label_single_line;
        }
    }

    @LayoutRes
    private int getOneLineIconLayout() {
        if (iconGravity == ICON_GRAVITY_END) {
            return R.layout.md_grid_list_label_single_line_icon_end;
        }
        return R.layout.md_grid_list_label_single_line_icon_start;
    }

    @LayoutRes
    private int getTwoLineIconLayout() {
        if (iconGravity == ICON_GRAVITY_END) {
            return R.layout.md_grid_list_label_two_line_same_icon_end;
        }
        return R.layout.md_grid_list_label_two_line_same_icon_start;
    }

    private void findChildViews() {
        primaryTextView = (TextView) findViewById(R.id.grid_list_label_line_1);
        secondaryTextView = (TextView) findViewById(R.id.grid_list_label_line_2);
        iconView = (ImageView) findViewById(R.id.grid_list_label_icon);
    }

    /**
     * Get the variant being displayed.
     *
     * @return The variant.
     */
    @GridItemVariant
    public int getVariant() {
        return variant;
    }

    /**
     * Set the variant to be displayed.
     *
     * @param variant The variant.
     */
    public void setVariant(@GridItemVariant int variant) {
        this.variant = variant;
        @LayoutRes int layout = getLayoutFromVariant(variant);
        removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(layout, this);
        findChildViews();
        setTexts();
        setIcon(icon);
    }

    private void setTexts() {
        setPrimaryText(primaryText);
        setPrimaryTextColors();
        setSecondaryText(secondaryText);
        setSecondaryTextColors();
    }

    private void setPrimaryTextColors() {
        if (primaryTextColorStateList != null) {
            setPrimaryTextColor(primaryTextColorStateList);
        }
        if (primaryTextColor != 0) {
            setPrimaryTextColor(primaryTextColor);
        }
    }

    private void setSecondaryTextColors() {
        if (secondaryTextColorStateList != null) {
            setSecondaryTextColor(secondaryTextColorStateList);
        }
        if (secondaryTextColor != 0) {
            setSecondaryTextColor(secondaryTextColor);
        }
    }

    /**
     * Get the primary text (line 1).
     *
     * @return The primary text.
     */
    public CharSequence getPrimaryText() {
        return primaryTextView.getText();
    }

    /**
     * Set the primary text (line 1).
     *
     * @param text The primary text.
     */
    public void setPrimaryText(CharSequence text) {
        primaryText = text;
        primaryTextView.setText(text);
    }

    /**
     * Set the primary text (line 1).
     *
     * @param textRes The primary text.
     */
    public void setPrimaryText(@StringRes int textRes) {
        setPrimaryText(getContext().getString(textRes));
    }

    /**
     * Get the primary text color state list (line 1).
     *
     * @return The primary text color state list.
     */
    public ColorStateList getPrimaryTextColors() {
        return primaryTextView.getTextColors();
    }

    /**
     * Get the primary text color (line 1).
     *
     * @return The primary text color.
     */
    @ColorInt
    public int getCurrentPrimaryTextColor() {
        return primaryTextView.getCurrentTextColor();
    }

    /**
     * Set the primary text color (line 1).
     *
     * @param color The primary text color.
     */
    public void setPrimaryTextColor(ColorStateList color) {
        primaryTextColorStateList = color;
        primaryTextView.setTextColor(color);
    }

    /**
     * Set the primary text color (line 1).
     *
     * @param color The primary text color.
     */
    public void setPrimaryTextColor(@ColorInt int color) {
        primaryTextColor = color;
        primaryTextView.setTextColor(color);
    }

    /**
     * Set the primary text color (line 1).
     *
     * @param colorRes The primary text color.
     */
    public void setPrimaryTextColorRes(@ColorRes int colorRes) {
        setPrimaryTextColor(getColor(colorRes));
    }

    /**
     * Get the secondary text (line 2).
     *
     * @return The secondary text.
     */
    public CharSequence getSecondaryText() {
        if (secondaryTextView != null) {
            return secondaryTextView.getText();
        }
        return null;
    }

    /**
     * Set the secondary text (line 2).
     *
     * @param text The secondary text.
     */
    public void setSecondaryText(CharSequence text) {
        secondaryText = text;
        if (secondaryTextView != null) {
            secondaryTextView.setText(text);
        }
    }

    /**
     * Set the secondary text (line 2).
     *
     * @param textRes The secondary text.
     */
    public void setSecondaryText(@StringRes int textRes) {
        setSecondaryText(getContext().getString(textRes));
    }

    /**
     * Get the secondary text color state list (line 2).
     *
     * @return The secondary text color state list.
     */
    public ColorStateList getSecondaryTextColors() {
        if (secondaryTextView != null) {
            return secondaryTextView.getTextColors();
        }
        return null;
    }

    /**
     * Get the secondary text color (line 2).
     *
     * @return The secondary text color.
     */
    @ColorInt
    public int getCurrentSecondaryTextColor() {
        if (secondaryTextView != null) {
            return secondaryTextView.getCurrentTextColor();
        }
        return 0;
    }

    /**
     * Set the secondary text color (line 2).
     *
     * @param color The secondary text color.
     */
    public void setSecondaryTextColor(ColorStateList color) {
        secondaryTextColorStateList = color;
        if (secondaryTextView != null) {
            secondaryTextView.setTextColor(color);
        }
    }

    /**
     * Set the secondary text color (line 2).
     *
     * @param color The secondary text color.
     */
    public void setSecondaryTextColor(@ColorInt int color) {
        secondaryTextColor = color;
        if (secondaryTextView != null) {
            secondaryTextView.setTextColor(color);
        }
    }

    /**
     * Set the secondary text color (line 2).
     *
     * @param colorRes The secondary text color.
     */
    public void setSecondaryTextColorRes(@ColorRes int colorRes) {
        setSecondaryTextColor(getColor(colorRes));
    }

    /**
     * Get the icon being displayed.
     *
     * @return The displayed icon.
     */
    public Drawable getIcon() {
        if (iconView != null) {
            return iconView.getDrawable();
        }
        return null;
    }

    /**
     * Set the icon to display at the end (on the right).
     *
     * @param icon The icon to display.
     */
    public void setIcon(Drawable icon) {
        this.icon = icon;
        if (iconView != null) {
            iconView.setImageDrawable(icon);
        }
    }

    /**
     * Set the icon to display at the end (on the right).
     *
     * @param iconRes The icon to display.
     */
    public void setIcon(@DrawableRes int iconRes) {
        setIcon(ContextCompat.getDrawable(getContext(), iconRes));
    }

    /**
     * Get the icon gravity. Can be start (to display before the text - or on the left), or
     * can be end (to display after the text - or on the right).
     *
     * @return The gravity of the icon.
     */
    @IconGravity
    public int getIconGravity() {
        return iconGravity;
    }

    /**
     * Set the gravity of the icon. Can be start (to display before the text - or on the left), or
     * can be end (to display after the text - or on the right).
     *
     * @param iconGravity The gravity for the icon.
     */
    public void setIconGravity(@IconGravity int iconGravity) {
        this.iconGravity = iconGravity;
        setVariant(variant);
    }

    private int getColor(@ColorRes int colorRes) {
        return ContextCompat.getColor(getContext(), colorRes);
    }

}
