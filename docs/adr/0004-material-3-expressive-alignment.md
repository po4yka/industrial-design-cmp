# ADR 0004: Material 3 Expressive alignment

## Status

Accepted — 2026-05-19

## Context

Compose Multiplatform 1.11.0 bundles Material 3 1.10.0-alpha05. Material 3 1.5 (alpha)
promoted several "expressive" components — `ToggleButton`, `ButtonGroup`, `FAB Menu`,
expressive menus — from experimental to stable on Android. A subset of these surfaces
overlap components already provided by this design system.

The industrial design vocabulary (Doto / Space Grotesk / Space Mono typography,
instrument-panel borders, monochrome palette, zero Material elevation) is intentionally
incompatible with M3 Expressive's rounded, colour-saturated defaults. Consumers need a
clear policy: which components to take from this library, which to take from M3, and
which to avoid mixing.

## Decision

Industrial components **supersede** the equivalent M3 surface where a bespoke
implementation exists. Where no industrial equivalent exists, consumers **adopt the M3
component directly** — `IndustrialTheme` already maps `MaterialTheme.colorScheme` so
M3 components inherit the monochrome palette without further configuration.

| Surface area | Industrial component | M3 counterpart | Policy |
|---|---|---|---|
| Segmented selection | `IndustrialSegmentedControl` | `SingleChoiceSegmentedButtonRow` | Supersede — Doto type, hard borders, no M3 elevation |
| Primary action | `IndustrialPrimaryButton` | `FilledButton` | Supersede |
| Secondary action | `IndustrialSecondaryButton` | `OutlinedButton` | Supersede |
| Ghost / tertiary | `IndustrialGhostButton` | `TextButton` | Supersede |
| Destructive action | `IndustrialDestructiveButton` | `FilledButton` (error colour) | Supersede |
| Bottom navigation | `IndustrialBottomBar` | `NavigationBar` | Supersede |
| Top navigation | `IndustrialTopNav` | `TopAppBar` | Supersede |
| Modal dialog | `IndustrialDialog` | `AlertDialog` | Wrap — M3 structure, industrial chrome |
| Bottom sheet | `IndustrialBottomSheet` | `ModalBottomSheet` | Wrap — M3 structure, industrial chrome |
| Checkbox | `IndustrialCheckbox` | `Checkbox` | Supersede |
| Toggle switch | `IndustrialSwitch` | `Switch` | Supersede |
| Toggle button | _(none)_ | `ToggleButton` (M3 expressive) | Adopt from M3 |
| Button group | _(none)_ | `ButtonGroup` (M3 expressive) | Adopt from M3 |
| FAB menu | _(none)_ | `FloatingActionButtonMenu` (M3 expressive) | Adopt from M3 |
| Badges | _(none)_ | M3 `BadgedBox` | Adopt from M3 |
| Search bar | _(none)_ | M3 `SearchBar` / `DockedSearchBar` | Adopt from M3 |

**Explicit "do not adopt" cases:**

- **M3 expressive loading indicators** (`LoadingIndicator`, `ContainedLoadingIndicator`).
  These use colour-filled, pill-shaped animations that conflict with the industrial
  linear-progress style. Use `IndustrialLinearProgress` instead.
- **M3 expressive `WideNavigationRail`** — its rounded, spacious form language
  contradicts the tight instrument-panel grid. Prefer `IndustrialTopNav` or a custom
  rail that matches the existing border/spacing tokens.

**Import boundary:** restrict industrial component imports to screens that are fully
committed to the design system. Do not place an `IndustrialPrimaryButton` adjacent to a
plain M3 `FilledButton` on the same screen — the typography and border treatment will
visually conflict. If a screen mixes M3 and industrial surfaces for any reason (e.g.,
third-party SDK sheet), apply `IndustrialTheme` at the industrial subtree boundary only.

## Consequences

**Clearer:**
- Consumers have a definitive lookup table; no per-component guessing.
- New M3 expressive components that appear in future CMP releases default to "adopt
  from M3" unless a superseding industrial component is added and this ADR updated.
- The "wrap" policy for Dialog and BottomSheet keeps structural accessibility semantics
  (dismissal, focus trap) from M3 while the visual chrome matches the system.

**Harder:**
- Superseded components must be kept up-to-date as M3 updates their behaviour contracts
  (accessibility roles, state semantics). Industrial implementations carry this burden.
- Consumers using a third-party component that renders M3 surfaces internally will see
  a colour-only integration (palette mapped, but type and shape unchanged). Document this
  limitation in the consuming app's own design guidelines.

## Alternatives considered

**Full M3 Expressive adoption** — rejected. The expressive design language (rounded
shapes, colour saturation, spring-physics motion) is aesthetically incompatible with the
industrial vocabulary. Adopting it wholesale would erase the system's identity.

**Zero M3 dependency** — rejected. Reimplementing M3's accessibility primitives
(focus, traversal, semantics roles), sheet scaffolding, and theme slot system from
scratch would be a large surface area with no design-system benefit. Wrapping M3 where
the structure matters and superseding only the visual surface is the correct trade-off.

## See also

- ADR 0001 — Component maturity labels.
- ADR 0002 — No animations by default.
- ADR 0003 — Maven Central publishing.
- `library/src/commonMain/kotlin/com/po4yka/industrialdesign/theme/Theme.kt` — where
  `IndustrialTheme` maps industrial tokens into `MaterialTheme`.
