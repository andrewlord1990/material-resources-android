# Changelog

## v0.5.0 (03/11/16)

### Resources

- Library will now focus only on the commonly used components and resources
- Replaced the prefix `md_` on resources with `ma_`
- Replaced the prefix `MD` in styles to `MA`
- Tidied up the grid and list layout files
- Added resources for grid and list text colours, so that you can override them
- Added a screen edge vertical margin dimension

### Dependencies

- Update to target Api 25
- Updated Android support library dependencies to v25.0.0

## v0.4.0 (31/07/16)

### Custom Views

- Password input field (with text visibility toggle)
- List items (with many variants)
- Grid list items (with multiple variants)

### Resources

- Text fields
- Full width text field style to apply to `EditText` views.
- Tabs
- Subheaders
- Bottom sheets
- Bottom navigation
- Data tables

### Samples

- Text fields (full width text field style)
- Password input field (showing different options)
- Added subheaders to lists sample
- List and grid item samples using new custom views

### Improvements

- Code quality checking using CheckStyle, FindBugs and PMD
- Reformatted code style

### Dependencies

- Update to target Api 24
- Updated Android support library dependencies to v24.1.1

## v0.3.0 (21/03/16)

### Code

- Code constants for text colors. E.g. `MaterialColor.BLACK_87`.
- Create a `MaterialColor` object, to make it clear you would like a member of the Material Color Palette (not enforced though, just to show intent).

### Resources

- All library layout files now have "md_" prefix.
- Grid lists: Cell layout files (multiple variants) and all resources. (Include them straight into your app.)
- Menus: dimension resources.
- Snackbars: dimension and colour resources.
- Buttons: dimension and colour resources.
- Subheaders: dimension and colour resources.
- Chips: dimension resources.
- Tooltips: dimension and colour resources.
- Dialogs: dimension resources.
- Dividers: layouts and dimension resources. (Include them into your layouts.)
- Cards: dimension resources.

### Samples

- Grid lists: multiple variants, FAB to switch between headers and footers on the cells.

### Project

- Added Support-v7 AppCompat dependency.


## v0.2.0 (18/03/16)

- Sample app: Test out combinations of primary and accent colour, view the text sizes and colours with both light and dark backgrounds.
- Lists: dimensions, text sizes, colours and layouts. (Including sample showing them all.)
- Code constants of the Material palette colours.


## v0.1.0 (15/03/16)

- Material palette colours as resources.
- Text colours as resources.
- Icon and divider colours as resources.
- Text sizes for various types of text (e.g. titles, headings, body) as resources.
- Basic set of page margins as resources.
