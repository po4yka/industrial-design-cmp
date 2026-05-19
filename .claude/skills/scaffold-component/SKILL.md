---
name: scaffold-component
description: Scaffold a new industrial-design-cmp component end-to-end — composable + theme wiring + Preview entries + screenshot-test entry. Invoke with /scaffold-component <ComponentName> <category>.
---

# /scaffold-component

## Arguments
- `<ComponentName>` — PascalCase, will be prefixed with `Industrial` in the public API (e.g. `ButtonGroup` -> `IndustrialButtonGroup`).
- `<category>` — one of: `Buttons`, `Navigation`, `Inputs`, `Overlays`, `Progress`, `Gauges`, `Selection`, `Data`, `Foundation`. Determines which preview file to extend in `:preview-desktop`.
- **Library file is per-component, not per-category.** Existing files at `library/src/commonMain/kotlin/com/po4yka/industrialdesign/components/` include `Buttons.kt`, `Input.kt`, `Gauge.kt`, `Navigation.kt`, `Overlays.kt`, `SegmentedControl.kt`, `SegmentedProgress.kt`, `StatRow.kt`, `Table.kt`, `Tag.kt`, `Toggle.kt`, `Widget.kt`, `Card.kt`, `BarChart.kt`, `DotGrid.kt`. Either extend the closest existing file or create a new one named after the component (e.g. `ButtonGroup` -> `ButtonGroup.kt`).

## What this skill does
1. Open the matching component file in `:library` (`library/src/commonMain/kotlin/com/po4yka/industrialdesign/components/<Name>.kt`) and add the new composable.
2. Open the matching preview file in `:preview-desktop` (`preview-desktop/src/main/kotlin/com/po4yka/industrialdesign/preview/<Category>Preview.kt`) and add at least two `@Preview` entries (typical / disabled or alt state).
3. Remind the user to run `./gradlew :library:apiDump` so the new public API lands in `library/api/library.klib.api` and `library/api/desktop/library.api`.
4. Remind the user to run `./gradlew :screenshot-tests:recordRoborazziDebug` so ComposablePreviewScanner picks up the new previews and writes golden PNGs.

## Pattern to follow

### 1. Composable signature
Mirror `Buttons.kt`. Rules observed there:
- Public name prefixed `Industrial...` (e.g. `IndustrialPrimaryButton`).
- Parameter order: behavior callbacks first (`onClick`), then content/state (`text`), then `modifier: Modifier = Modifier`, then `enabled: Boolean = true`, then trailing slots (`leadingIcon`, `trailingIcon`, `content`).
- Sized via `defaultMinSize(minHeight = 44.dp)` for tap targets; padding via a `private val ButtonContentPadding = PaddingValues(...)` style constant at file top.
- Colors come from `MaterialTheme.colorScheme.*`. Disabled state derives via `.copy(alpha = DisabledAlpha)` where `DisabledAlpha = 0.4f`.
- Radius tokens come from `IndustrialTokens.Radius.*` (e.g. `Pill`, `Technical`). Never hard-code corner sizes.
- KDoc above each composable: one paragraph describing visual treatment + key tokens used.
- When a component has multiple visual variants, expose two overloads: `(onClick, text, ...)` and `(onClick, modifier, ..., content: @Composable RowScope.() -> Unit)`. The text overload calls the content overload with a shared private helper (see `industrialButtonLabel`).
- `elevation = ButtonDefaults.buttonElevation(... = 0.dp)` — flat by design. No shadows.

### 2. Preview entries
Mirror `ButtonsPreview.kt`. Rules:
- Use `androidx.compose.ui.tooling.preview.Preview` (Jetpack import — NOT the JetBrains one).
- Every preview wraps content in `PreviewHost { ... }` (defined in `PreviewHost.kt`, applies `IndustrialTheme` + background Box).
- Ship at minimum: one typical-state preview, one disabled or alt-state preview. Add a light-theme variant (`PreviewHost(darkTheme = false) { ... }`) when color contrast differs meaningfully between modes.
- Preview functions are top-level `fun ...Preview()` (no class wrapper), public, return Unit via `= PreviewHost { ... }`.
- Spacing tokens use `IndustrialTokens.Spacing.{xs,sm,md,lg,...}`; do not hard-code dp.

### 3. Screenshot coverage
`:screenshot-tests` uses ComposablePreviewScanner to auto-discover every `@Preview` in `:preview-desktop`. No manual screenshot test file is required. After adding previews, run:
```
./gradlew :screenshot-tests:recordRoborazziDebug
```
Golden PNGs land under `screenshot-tests/src/test/snapshots/` (or the project's configured output dir). Commit them alongside the component.

### 4. Public API impact + binary compatibility
The new public composables will appear in the next `./gradlew :library:apiDump`. BCV is wired and `apiCheck` runs on CI, so the PR will fail until the regenerated `library/api/library.klib.api` and `library/api/desktop/library.api` are committed. Always:
```
./gradlew :library:apiDump
git add library/api
```

## Checklist
- [ ] Composable added at `library/src/commonMain/kotlin/com/po4yka/industrialdesign/components/<File>.kt` following the `Buttons.kt` shape (Modifier-first, theme tokens, 44dp min target, flat elevation, KDoc).
- [ ] At least 2 `@Preview` entries added to `preview-desktop/src/main/kotlin/com/po4yka/industrialdesign/preview/<Category>Preview.kt` using `PreviewHost { ... }` and `androidx.compose.ui.tooling.preview.Preview`.
- [ ] `./gradlew :library:apiDump` run and the `library/api/*.api` diff staged.
- [ ] `./gradlew :screenshot-tests:recordRoborazziDebug` run on a workstation with Android SDK; golden PNGs committed.
