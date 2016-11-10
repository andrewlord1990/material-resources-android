# Material Android

[![Build Status](https://travis-ci.org/andrewlord1990/materialandroid.svg?branch=master)](https://travis-ci.org/andrewlord1990/materialandroid)
[![Coverage Status](https://coveralls.io/repos/github/andrewlord1990/materialandroid/badge.svg?branch=master&v=2)](https://coveralls.io/github/andrewlord1990/materialandroid?branch=master)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Material%20Android-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/4013)
[![License](https://img.shields.io/badge/license-Apache%202.0-green.svg) ](https://github.com/andrewlord1990/materialandroid/blob/master/LICENSE)
[![API](https://img.shields.io/badge/API-9%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=9)
[ ![Download](https://api.bintray.com/packages/andrewlord1990/maven/materialandroid/images/download.svg) ](https://bintray.com/andrewlord1990/maven/materialandroid/_latestVersion)

Access commonly used Material Design resource values (e.g. colours, text sizes and dimensions), custom views, layouts and styles, through a simple dependency.

## Download

Download via Gradle:
```groovy
compile 'com.github.andrewlord1990:materialandroid:0.5.0'
```

Available on Bintray (JCenter).
```groovy
repositories {
  jcenter()
}
```

## Contents

There are various ways in which Material Android can be useful to you:

1. Include as a dependency to access all the resources, layouts and views.
2. Use as a reference to add the resouces you need to your app.
3. Run the sample app to try out different primary and accent colour combinations or to test out the different styles and views.

### [Colour palette]

All the material colours are included both as colour resources and through the `MaterialColor` class. The colour resources are named in the form ma_{colourName}_{colourValue}

For example:

@color/ma_red_500, @color/ma_blue_700, @color/ma_purple_a200, R.color.ma_orange_100, R.color.ma_teal_a700

All of the colours and colour values in the palette are included, both regular values and accent versions. Black and white, of course only have a single value.

### [Typography] and [Text colours]

Text colours are included for display on both light and dark backgrounds. Material Design suggests changing text colour through opacity, so that the text displays well on different coloured backgrounds.

**Dark Text on a Light Background**

| Style                | Colour  | Opacity | Name                           |
| -------------------- | ------- | ------- | ------------------------------ |
| Primary text         | #000000 | 87%     | ma_text_dark_primary_87        |
| Secondary text       | #000000 | 54%     | ma_text_dark_secondary_54      |
| Disabled / Hint text | #000000 | 38%     | ma_text_dark_disabled_hint_38  |

**Light Text on a Dark Background**

| Style                | Colour  | Opacity | Name                           |
| -------------------- | ------- | ------- | ------------------------------ |
| Primary text         | #ffffff | 100%    | ma_text_light_primary_100      |
| Secondary text       | #ffffff | 70%     | ma_text_light_secondary_70     |
| Disabled / Hint text | #ffffff | 50%     | ma_text_light_disabled_hint_50 |


Text sizes are available for different types of text, such as buttons, captions and titles, as well.

### Keylines and Components

Styling for various commonly used components are included, through layouts, dimensions, colours and text sizes.

- Buttons
- Cards
- Dividers
- Floating Action Button
- Grid Lists
- Lists
- Subheaders
- Text Fields

### Styles

A full width text field style has been applied which you can use with an `EditText`. This removes the underline and displays it as a full-width input field. You can see it in action within the sample app.

### Custom Views

#### PasswordEditText

An `EditText` class which meets the Material Design guidelines for a password input field. It features a visibility toggle that switches between hiding and showing the text entered. It features customisation options to change the toggle and the default visibility state for the text. Support for this type of input have been added to the Android support libraries, however, the version here is still more powerful. It allows you to have greater control over the appearance of the visibility toggle. 

1. You can have the icon change opacity based on the visibility state.
2. The icon can have strike-through added when the input is hidden.
3. You can choose two custom drawables to use for the two visibility states.

#### GridItemView

A view which meets the Material Design guidelines for grid lists. It contains multiple different variants and other customisation options. As mentioned above, layouts are also provided to add grid list item views to your app if you don't wish to use the full custom view.

- Single line of text
- Single line of text with an icon
- Two lines of text
- Two lines of text with an icon
- Icon gravity (either left/start or right/end)

#### ListItemView

A view which meets the Material Design guidelines for lists. It contains many different variants and other customisation options. As mentioned above, layouts are also provided to add list item views to your app if you don't wish to use the full custom view.

- One, two or three lines of text
- Icon (on right/end)
- Avatar (on left/start)
- Icon and avatar

## Sample

The sample app contains many different parts. It allows you to try out the Material Design palette - you can select a primary and accent colour and see what they look like next to each other.

You can try out the different typography on both a light and dark background.

You can use the different components included:

- The password input field
- Grid lists
- Lists
- A full width text field


[Colour palette]: https://www.google.com/design/spec/style/color.html#color-color-palette
[Text colours]: https://www.google.com/design/spec/style/color.html#color-text-background-colors
[Typography]: https://www.google.com/design/spec/style/typography.html#typography-styles
