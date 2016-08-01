# Material Android

[![Build Status](https://travis-ci.org/andrewlord1990/materialandroid.svg?branch=master)](https://travis-ci.org/andrewlord1990/materialandroid)
[![Coverage Status](https://coveralls.io/repos/github/andrewlord1990/materialandroid/badge.svg?branch=master?v=2)](https://coveralls.io/github/andrewlord1990/materialandroid?branch=master)
[![License](https://img.shields.io/badge/license-Apache%202.0-green.svg) ](https://github.com/andrewlord1990/materialandroid/blob/master/LICENSE)
[![API](https://img.shields.io/badge/API-7%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=7)
[ ![Download](https://api.bintray.com/packages/andrewlord1990/maven/materialandroid/images/download.svg) ](https://bintray.com/andrewlord1990/maven/materialandroid/_latestVersion)

Access Material Design resource values, such as colours, text sizes and dimensions, through a simple dependency.

## Download

Download via Gradle:
```groovy
compile 'com.github.andrewlord1990:materialandroid:0.4.0'
```

Available on Bintray (JCenter).
```groovy
repositories {
  jcenter()
}
```

## Contents

### [Colour palette]

All the material colours are included both as colour resources and through the `MaterialColor` class. The colour resources are named in the form md_{colourName}_{colourValue}

For example:

XML: @color/md_red_500, @color/md_blue_700, @color/md_purple_a200
Code: R.color.md_orange_100, R.color.md_teal_a700

All of the colours and colour values in the palette are included, both regular values and accent versions. Black and white, of course only have a single value.

### [Typography] and [Text colours]

Text colours are included for display on both light and dark backgrounds. Material Design suggests changing text colour through opacity, so that the text displays well on different coloured backgrounds.

**Dark Text on a Light Background**

| Style                | Colour  | Opacity | Name                           |
| -------------------- | ------- | ------- | ------------------------------ |
| Primary text         | #000000 | 87%     | md_dark_primary_text_87        |
| Secondary text       | #000000 | 54%     | md_dark_secondary_text_54      |
| Disabled / Hint text | #000000 | 38%     | md_dark_disabled_hint_text_38  |

**Light Text on a Dark Background**

| Style                | Colour  | Opacity | Name                           |
| -------------------- | ------- | ------- | ------------------------------ |
| Primary text         | #ffffff | 100%    | md_light_primary_text_100      |
| Secondary text       | #ffffff | 70%     | md_light_secondary_text_70     |
| Disabled / Hint text | #ffffff | 50%     | md_light_disabled_hint_text_50 |


Text colours and sizes are available for different types of text, such as buttons, captions and titles, as well.

### Keylines and Components

Styling for various different components are included, through layouts, dimensions, colours and text sizes.

- Bottom Navigation
- Bottom Sheets
- Buttons
- Cards
- Chips
- Data Tables
- Dialogs
- Dividers
- Floating Action Button
- Grid Lists
- Lists
- Menus
- Snackbars
- Subheaders
- Tabs
- Text Fields
- Tooltips

### Styles

A full width text field style has been applied which you can use with an `EditText`. This removes the underline and displays it as a full-width input field. You can see it in action within the sample app.

### Custom Views

#### PasswordEditText

An `EditText` class which meets the Material Design guidelines for a password input field. It features a visibility toggle that switches between hiding and showing the text entered. It features customisation options to change the toggle and the default visibility state for the text.

#### GridItemView

A view which meets the Material Design guidelines for grid lists. It contains multiple different variants and other customisation options.

- Single line of text
- Single line of text with an icon
- Two lines of text
- Two lines of text with an icon
- Icon gravity (either left/start or right/end)

#### ListItemView

A view which meets the Material Design guidelines for lists. It contains many different variants and other customisation options.

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
