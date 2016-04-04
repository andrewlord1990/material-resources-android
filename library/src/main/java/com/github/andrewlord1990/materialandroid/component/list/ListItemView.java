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

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
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
 * A view that meets the Material Design specification for a list item view. It supports many
 * different variants, containing one to three lines of text, an icon at the end (right) and an
 * avatar at the start (left).
 */
public class ListItemView extends FrameLayout {

    public static final int VARIANT_ONE_LINE_TEXT = 0;
    public static final int VARIANT_ONE_LINE_TEXT_ICON = 1;
    public static final int VARIANT_ONE_LINE_TEXT_AVATAR = 2;
    public static final int VARIANT_ONE_LINE_TEXT_ICON_AVATAR = 3;
    public static final int VARIANT_TWO_LINE_TEXT = 4;
    public static final int VARIANT_TWO_LINE_TEXT_ICON = 5;
    public static final int VARIANT_TWO_LINE_TEXT_AVATAR = 6;
    public static final int VARIANT_TWO_LINE_TEXT_ICON_AVATAR = 7;
    public static final int VARIANT_THREE_LINE_TEXT = 8;
    public static final int VARIANT_THREE_LINE_TEXT_ICON = 9;
    public static final int VARIANT_THREE_LINE_TEXT_AVATAR = 10;
    public static final int VARIANT_THREE_LINE_TEXT_ICON_AVATAR = 11;

    @IntDef({VARIANT_ONE_LINE_TEXT, VARIANT_ONE_LINE_TEXT_ICON, VARIANT_ONE_LINE_TEXT_AVATAR,
            VARIANT_ONE_LINE_TEXT_ICON_AVATAR, VARIANT_TWO_LINE_TEXT, VARIANT_TWO_LINE_TEXT_ICON,
            VARIANT_TWO_LINE_TEXT_AVATAR, VARIANT_TWO_LINE_TEXT_ICON_AVATAR,
            VARIANT_THREE_LINE_TEXT, VARIANT_THREE_LINE_TEXT_ICON, VARIANT_THREE_LINE_TEXT_AVATAR,
            VARIANT_THREE_LINE_TEXT_ICON_AVATAR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ListItemVariant {
    }

    private TextView primaryTextView;
    private TextView secondaryTextView;
    private TextView tertiaryTextView;
    private ImageView iconView;
    private ImageView avatarView;

    private CharSequence primaryText;
    private CharSequence secondaryText;
    private CharSequence tertiaryText;
    private Drawable icon;
    private Drawable avatar;

    @ListItemVariant
    private int variant;

    /**
     * Create a list item view using the default settings, which can then be customised later.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public ListItemView(Context context) {
        super(context);
        loadDefaults();
    }

    /**
     * Create a list item view through XML inflation using settings from provided
     * attributes and from the style assigned to the theme attribute mdListItemViewStyle.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     */
    public ListItemView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.mdListItemViewStyle);
    }

    /**
     * Create a list item view through XML inflation using settings from provided
     * attributes and from the style assigned to the specified theme attribute.
     *
     * @param context      The Context the view is running in, through which it can
     *                     access the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     *                     the view. Can be 0 to not look for defaults.
     */
    public ListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadThemeAttributes(attrs, defStyleAttr, 0);
    }

    /**
     * Create a list item view through XML inflation using settings from provided
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
    public ListItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        loadThemeAttributes(attrs, defStyleAttr, defStyleRes);
    }

    private void loadThemeAttributes(AttributeSet attrs, @AttrRes int themeAttribute, @StyleRes int styleRes) {
        TypedArray typedAttrs = getContext().getTheme().obtainStyledAttributes(
                attrs, R.styleable.MDListItemView, themeAttribute, styleRes);
        try {
            loadVariant(typedAttrs);
            loadText(typedAttrs);
            loadIcon(typedAttrs);
            loadAvatar(typedAttrs);
        } finally {
            typedAttrs.recycle();
        }
    }

    private void loadVariant(TypedArray typedAttrs) {
        @ListItemVariant
        int variant = typedAttrs.getInt(R.styleable.MDListItemView_md_list_item_variant,
                VARIANT_ONE_LINE_TEXT);
        setVariant(variant);
    }

    private void loadText(TypedArray typedAttrs) {
        String primaryText = typedAttrs.getString(R.styleable.MDListItemView_md_list_text_primary);
        setPrimaryText(primaryText);
        String secondaryText = typedAttrs.getString(R.styleable.MDListItemView_md_list_text_secondary);
        setSecondaryText(secondaryText);
        String tertiaryText = typedAttrs.getString(R.styleable.MDListItemView_md_list_text_tertiary);
        setTertiaryText(tertiaryText);
    }

    private void loadIcon(TypedArray typedAttrs) {
        Drawable icon = typedAttrs.getDrawable(R.styleable.MDListItemView_md_list_icon);
        setIcon(icon);
    }

    private void loadAvatar(TypedArray typedAttrs) {
        Drawable avatar = typedAttrs.getDrawable(R.styleable.MDListItemView_md_list_avatar);
        setAvatar(avatar);
    }

    private void loadDefaults() {
        setVariant(VARIANT_ONE_LINE_TEXT);
    }

    @LayoutRes
    private int getLayoutFromVariant(@ListItemVariant int variant) {
        switch (variant) {
            case VARIANT_ONE_LINE_TEXT:
                return R.layout.md_list_single_line;
            case VARIANT_ONE_LINE_TEXT_ICON:
                return R.layout.md_list_single_line_icon;
            case VARIANT_ONE_LINE_TEXT_AVATAR:
                return R.layout.md_list_single_line_avatar;
            case VARIANT_ONE_LINE_TEXT_ICON_AVATAR:
                return R.layout.md_list_single_line_avatar_and_icon;
            case VARIANT_TWO_LINE_TEXT:
                return R.layout.md_list_two_line;
            case VARIANT_TWO_LINE_TEXT_ICON:
                return R.layout.md_list_two_line_icon;
            case VARIANT_TWO_LINE_TEXT_AVATAR:
                return R.layout.md_list_two_line_avatar;
            case VARIANT_TWO_LINE_TEXT_ICON_AVATAR:
                return R.layout.md_list_two_line_avatar_and_icon;
            case VARIANT_THREE_LINE_TEXT:
                return R.layout.md_list_three_line;
            case VARIANT_THREE_LINE_TEXT_ICON:
                return R.layout.md_list_three_line_icon;
            case VARIANT_THREE_LINE_TEXT_AVATAR:
                return R.layout.md_list_three_line_avatar;
            case VARIANT_THREE_LINE_TEXT_ICON_AVATAR:
                return R.layout.md_list_three_line_avatar_and_icon;
            default:
                return R.layout.md_list_single_line;
        }
    }

    private void findChildViews() {
        primaryTextView = (TextView) findViewById(R.id.list_primary_text);
        secondaryTextView = (TextView) findViewById(R.id.list_secondary_text);
        tertiaryTextView = (TextView) findViewById(R.id.list_tertiary_text);
        iconView = (ImageView) findViewById(R.id.list_icon);
        avatarView = (ImageView) findViewById(R.id.list_avatar);
    }

    /**
     * Get the variant being displayed.
     *
     * @return The variant.
     */
    @ListItemVariant
    public int getVariant() {
        return variant;
    }

    /**
     * Set the variant to be displayed.
     *
     * @param variant The variant.
     */
    public void setVariant(@ListItemVariant int variant) {
        this.variant = variant;
        @LayoutRes int layout = getLayoutFromVariant(variant);
        removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(layout, this);
        findChildViews();
        setTexts();
        setDrawables();
    }

    private void setTexts() {
        setPrimaryText(primaryText);
        setSecondaryText(secondaryText);
        setTertiaryText(tertiaryText);
    }

    private void setDrawables() {
        setIcon(icon);
        setAvatar(avatar);
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
     * Get the tertiary text (line 3).
     *
     * @return The tertiary text.
     */
    public CharSequence getTertiaryText() {
        if (tertiaryTextView != null) {
            return tertiaryTextView.getText();
        }
        return null;
    }

    /**
     * Set the tertiary text (line 3).
     *
     * @param text The tertiary text.
     */
    public void setTertiaryText(CharSequence text) {
        tertiaryText = text;
        if (tertiaryTextView != null) {
            tertiaryTextView.setText(text);
        }
    }

    /**
     * Set the tertiary text (line 3).
     *
     * @param textRes The tertiary text.
     */
    public void setTertiaryText(@StringRes int textRes) {
        setTertiaryText(getContext().getString(textRes));
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
     * Get the avatar being displayed.
     *
     * @return The displayed avatar.
     */
    public Drawable getAvatar() {
        if (avatarView != null) {
            return avatarView.getDrawable();
        }
        return null;
    }

    /**
     * Set the avatar to display at the start (on the left).
     *
     * @param avatar The avatar to display.
     */
    public void setAvatar(Drawable avatar) {
        this.avatar = avatar;
        if (avatarView != null) {
            avatarView.setImageDrawable(avatar);
        }
    }

    /**
     * Set the avatar to display at the start (on the left).
     *
     * @param avatarRes The avatar to display.
     */
    public void setAvatar(@DrawableRes int avatarRes) {
        setAvatar(ContextCompat.getDrawable(getContext(), avatarRes));
    }

}
